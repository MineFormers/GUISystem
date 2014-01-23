package de.mineformers.gui.api.factory;

import de.mineformers.gui.api.component.UIComponent;
import de.mineformers.gui.api.loader.UIEntry;
import de.mineformers.gui.api.loader.UILoader;
import de.mineformers.gui.api.loader.UIProperty;
import de.mineformers.gui.api.util.ReflectionHelper;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;

/**
 * GUISystem
 * <p/>
 * ComponentFactory
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class ComponentFactory<C extends UIComponent>
{

    protected HashMap<String, Property> properties;
    protected Class<? extends UIComponent> genericClass;

    public ComponentFactory(Class<? extends UIComponent> clazz)
    {
        genericClass = clazz;
        mapProperties();
    }

    public abstract C construct(UILoader loader, UIEntry entry);

    public void mapProperties()
    {
        if (properties == null)
            properties = new HashMap<String, Property>();

        Iterator<Field> fields = ReflectionHelper.getFieldsUpTo(genericClass, Object.class).iterator();
        while (fields.hasNext())
        {
            Field field = fields.next();
            field.setAccessible(true);
            UIProperty property = field.getAnnotation(UIProperty.class);
            if (property != null)
            {
                properties.put(property.name(), new Property(field.getName(), property.type(), property.defaultValue()));
            }
        }
    }

}
