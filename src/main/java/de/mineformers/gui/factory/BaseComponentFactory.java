package de.mineformers.gui.factory;

import de.mineformers.gui.component.UIComponent;
import de.mineformers.gui.xml.XMLEntry;
import de.mineformers.gui.xml.XMLLoader;

/**
 * GUISystem
 * <p/>
 * BaseComponentFactory
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BaseComponentFactory extends ComponentFactory<UIComponent> {

    public BaseComponentFactory(Class<? extends UIComponent> clazz) {
        super(clazz);
    }

    @Override
    public UIComponent construct(XMLLoader loader, XMLEntry entry) {
        try {
            UIComponent component = genericClass.newInstance();
            component.init();
            for (String key : entry.getProperties().keySet()) {
                if (this.properties.containsKey(key)) {
                    properties.get(key).assignValue(genericClass, component, entry.getProperty(key));
                }
            }
            return component;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
