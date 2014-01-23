package de.mineformers.gui.api.loader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * GUISystem
 * <p/>
 * UIEntry
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class UIEntry
{

    private String name;
    private HashMap<String, String> properties;
    private ArrayList<UIEntry> children;

    public UIEntry(String name)
    {
        this.name = name;
        properties = new HashMap<String, String>();
        children = new ArrayList<UIEntry>();
    }

    public String getName()
    {
        return name;
    }

    public void setProperty(String name, String value)
    {
        this.properties.put(name, value);
    }

    public String getProperty(String name)
    {
        return properties.get(name);
    }

    public void addChild(UIEntry entry)
    {
        children.add(entry);
    }

    public Set<UIEntry> getChildrenByName(String name)
    {
        HashSet<UIEntry> set = new HashSet<UIEntry>();
        for (UIEntry entry : children)
            if (entry.getName().equals(name))
                set.add(entry);
        return set;
    }

    public HashMap<String, String> getProperties()
    {
        return properties;
    }

    public ArrayList<UIEntry> children()
    {
        return children;
    }

}
