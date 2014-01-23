package de.mineformers.gui.api.util;

import java.util.HashMap;

/**
 * GUISystem
 * <p/>
 * PropertyHelper
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class PropertyHelper
{

    private HashMap<String, Object> properties;

    public PropertyHelper(HashMap<String, Object> properties)
    {
        this.properties = properties;
    }

    public <T> T get(String name, T defaultValue, Class<T> clazz)
    {
        return properties.containsKey(name) ? clazz.cast(properties.get(name)) : defaultValue;
    }

    public <T> T get(String name, T defaultValue)
    {
        return get(name, defaultValue, (Class<T>) defaultValue.getClass());
    }

}
