package de.mineformers.gui.xml;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.Reflection;
import de.mineformers.gui.component.UIComponent;
import de.mineformers.gui.factory.BaseComponentFactory;
import de.mineformers.gui.factory.ComponentFactory;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.util.HashMap;

/**
 * GUISystem
 * <p/>
 * XMLLoader
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class XMLLoader {

    private static HashMap<String, Class<? extends UIComponent>> aliases;
    private static HashMap<Class<? extends UIComponent>, ComponentFactory> factories;

    public static void addAlias(String alias, Class<? extends UIComponent> componentClass) {
        if (aliases == null)
            aliases = new HashMap<String, Class<? extends UIComponent>>();
        aliases.put(alias, componentClass);
    }

    public static void addFactory(Class<? extends UIComponent> componentClass, ComponentFactory factory) {
        if (factories == null)
            factories = new HashMap<Class<? extends UIComponent>, ComponentFactory>();
        factories.put(componentClass, factory);
    }

    public static void init() {
        loadDefaultAliases();
        loadDefaultFactories();
    }

    public static void loadDefaultAliases() {
        if (aliases == null)
            aliases = new HashMap<String, Class<? extends UIComponent>>();
        if (aliases.isEmpty())
            try {
                String currentPackage = Reflection.getPackageName(XMLLoader.class);
                ImmutableSet<ClassPath.ClassInfo> classes = ClassPath.from(XMLLoader.class.getClassLoader()).getTopLevelClassesRecursive(currentPackage.substring(0, currentPackage.lastIndexOf(".")) + ".component");
                for (ClassPath.ClassInfo clazz : classes) {
                    if (!clazz.getName().endsWith("UIComponent")) {
                        addAlias(clazz.getSimpleName(), (Class<? extends UIComponent>) Class.forName(clazz.getName()));
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
    }

    public static void loadDefaultFactories() {
        if (factories == null)
            factories = new HashMap<Class<? extends UIComponent>, ComponentFactory>();
    }

    private ResourceLocation file;

    public XMLLoader(ResourceLocation file) {
        this.file = file;
    }

    public UIComponent construct(XMLEntry root) {
        if (aliases.containsKey(root.getName())) {
            if (factories.containsKey(aliases.get(root.getName())))
                return factories.get(aliases.get(root.getName())).construct(this, root);
            else
                return new BaseComponentFactory(aliases.get(root.getName())).construct(this, root);
        } else {
            try {
                ComponentFactory factory = null;
                if (factories.get(Class.forName(root.getName())) != null)
                    factory = factories.get(UIComponent.class);
                else
                    factory = new BaseComponentFactory((Class<? extends UIComponent>) Class.forName(root.getName()));
                return factory.construct(this, root);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public XMLEntry load() {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(Minecraft.getMinecraft().getResourceManager().getResource(file).getInputStream());
            doc.getDocumentElement().normalize();

            Element rootElement = doc.getDocumentElement();
            XMLEntry root = new XMLEntry(rootElement.getTagName());
            for (int i = 0; i < rootElement.getAttributes().getLength(); i++) {
                root.setProperty(rootElement.getAttributes().item(i).getNodeName(), rootElement.getAttributes().item(i).getNodeValue());
            }

            for (int i = 0; i < rootElement.getChildNodes().getLength(); i++) {
                root.addChild(resolveNode(rootElement.getChildNodes().item(i)));
            }

            return root;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private XMLEntry resolveNode(Node node) {
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            XMLEntry entry = new XMLEntry(element.getTagName());
            for (int i = 0; i < element.getAttributes().getLength(); i++) {
                entry.setProperty(element.getAttributes().item(i).getLocalName(), element.getAttributes().item(i).getNodeValue());
            }
            for (int i = 0; i < element.getChildNodes().getLength(); i++) {
                entry.addChild(resolveNode(element.getChildNodes().item(i)));
            }
            return entry;
        }

        return null;
    }

}
