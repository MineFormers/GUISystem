package de.mineformers.gui.api.loader;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.Reflection;
import de.mineformers.gui.api.component.UIComponent;
import de.mineformers.gui.api.factory.BaseComponentFactory;
import de.mineformers.gui.api.factory.ComponentFactory;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * GUISystem
 * <p/>
 * UILoader
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class UILoader
{
    private static HashMap<String, Class<? extends UIComponent>> aliases;
    private static HashMap<Class<? extends UIComponent>, ComponentFactory> factories;

    public static void addAlias(String alias, Class<? extends UIComponent> componentClass)
    {
        if (aliases == null)
            aliases = new HashMap<String, Class<? extends UIComponent>>();
        aliases.put(alias, componentClass);
    }

    public static void addFactory(Class<? extends UIComponent> componentClass, ComponentFactory factory)
    {
        if (factories == null)
            factories = new HashMap<Class<? extends UIComponent>, ComponentFactory>();
        factories.put(componentClass, factory);
    }

    public static void init()
    {
        loadDefaultAliases();
        loadDefaultFactories();
    }

    public static void loadDefaultAliases()
    {
        if (aliases == null)
            aliases = new HashMap<String, Class<? extends UIComponent>>();
        if (aliases.isEmpty())
            try
            {
                String currentPackage = Reflection.getPackageName(UILoader.class);
                ImmutableSet<ClassPath.ClassInfo> classes = ClassPath.from(UILoader.class.getClassLoader()).getTopLevelClassesRecursive(currentPackage.substring(0, currentPackage.lastIndexOf(".")) + ".component");
                for (ClassPath.ClassInfo clazz : classes)
                {
                    if (!clazz.getName().endsWith("UIComponent"))
                    {
                        addAlias(clazz.getSimpleName(), (Class<? extends UIComponent>) Class.forName(clazz.getName()));
                    }
                }
            } catch (IOException e)
            {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e)
            {
                throw new RuntimeException(e);
            }
    }

    public static void loadDefaultFactories()
    {
        if (factories == null)
            factories = new HashMap<Class<? extends UIComponent>, ComponentFactory>();
    }

    protected ResourceLocation file;

    public UILoader(ResourceLocation file)
    {
        this.file = file;
    }

    public UIComponent construct() {
        return construct(load());
    }

    public UIComponent construct(UIEntry root)
    {
        if (aliases.containsKey(root.getName()))
        {
            if (factories.containsKey(aliases.get(root.getName())))
                return factories.get(aliases.get(root.getName())).construct(this, root);
            else
                return new BaseComponentFactory(aliases.get(root.getName())).construct(this, root);
        } else
        {
            try
            {
                ComponentFactory factory;
                if (factories.get(Class.forName(root.getName())) != null)
                    factory = factories.get(UIComponent.class);
                else
                    factory = new BaseComponentFactory((Class<? extends UIComponent>) Class.forName(root.getName()));
                return factory.construct(this, root);
            } catch (ClassNotFoundException e)
            {
                throw new RuntimeException(e);
            }

        }
    }

    public InputStream getStreamFromFile() throws IOException
    {
        return Minecraft.getMinecraft().getResourceManager().getResource(file).getInputStream();
    }

    public abstract UIEntry load();

}
