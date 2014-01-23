package de.mineformers.gui.api.loader;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * GUISystem
 * <p/>
 * UIProperty
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface UIProperty
{

    public static enum Type
    {

        INT
                {
                    @Override
                    public Object convert(String value)
                    {
                        return Integer.parseInt(value);
                    }

                    @Override
                    public String getDefaultValue()
                    {
                        return "0";
                    }
                },
        STRING
                {
                    @Override
                    public Object convert(String value)
                    {
                        return value;
                    }

                    @Override
                    public String getDefaultValue()
                    {
                        return "";
                    }
                };

        public abstract Object convert(String value);

        public abstract String getDefaultValue();

    }

    String name();

    Type type();

    String defaultValue() default "null";

}
