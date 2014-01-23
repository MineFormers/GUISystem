package de.mineformers.gui.api.loader;

import net.minecraft.util.ResourceLocation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * GUISystem
 * <p/>
 * XMLLoader
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class XMLLoader extends UILoader
{

    public XMLLoader(ResourceLocation file)
    {
        super(file);
    }

    @Override
    public UIEntry load()
    {
        try
        {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(this.getStreamFromFile());
            doc.getDocumentElement().normalize();

            Element rootElement = doc.getDocumentElement();
            UIEntry root = new UIEntry(rootElement.getTagName());
            for (int i = 0; i < rootElement.getAttributes().getLength(); i++)
            {
                root.setProperty(rootElement.getAttributes().item(i).getNodeName(), rootElement.getAttributes().item(i).getNodeValue());
            }

            for (int i = 0; i < rootElement.getChildNodes().getLength(); i++)
            {
                root.addChild(resolveNode(rootElement.getChildNodes().item(i)));
            }

            return root;
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    private UIEntry resolveNode(Node node)
    {
        if (node.getNodeType() == Node.ELEMENT_NODE)
        {
            Element element = (Element) node;
            UIEntry entry = new UIEntry(element.getTagName());
            for (int i = 0; i < element.getAttributes().getLength(); i++)
            {
                entry.setProperty(element.getAttributes().item(i).getLocalName(), element.getAttributes().item(i).getNodeValue());
            }
            for (int i = 0; i < element.getChildNodes().getLength(); i++)
            {
                entry.addChild(resolveNode(element.getChildNodes().item(i)));
            }
            return entry;
        }

        return null;
    }
}
