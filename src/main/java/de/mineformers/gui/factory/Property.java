package de.mineformers.gui.factory;

import de.mineformers.gui.component.UIComponent;
import de.mineformers.gui.util.ReflectionHelper;
import de.mineformers.gui.xml.XMLProperty;

import java.lang.reflect.Field;

/**
 * GUISystem
 * <p/>
 * Property
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Property {

    private String fieldName;
    private XMLProperty.Type type;
    private String defaultValue;

    public Property(String fieldName, XMLProperty.Type type, String defaultValue) {
        this.fieldName = fieldName;
        this.type = type;
        this.defaultValue = defaultValue.equals("null") ? null : defaultValue;
    }

    public void assignValue(Class<? extends UIComponent> clazz, Object instance, String value) {
        Field field = ReflectionHelper.getFieldFull(fieldName, clazz, Object.class);
        if (value == null)
            getDefaultValue();
        try {
            field.setAccessible(true);
            field.set(instance, type.convert(value));
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Can't access field: " + fieldName, e);
        }
    }

    public Object getDefaultValue() {
        return defaultValue == null ? type.getDefaultValue() : defaultValue;
    }

    @Override
    public String toString() {
        return "{ " + fieldName + " ; " + type + " ; DEFAULT: " + getDefaultValue() + " }";
    }
}
