package de.mineformers.gui.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * GUISystem
 * <p/>
 * XMLEntry
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class XMLEntry {

    private String name;
    private HashMap<String, String> properties;
    private ArrayList<XMLEntry> children;

    public XMLEntry(String name) {
        this.name = name;
        properties = new HashMap<String, String>();
        children = new ArrayList<XMLEntry>();
    }

    public String getName() {
        return name;
    }

    public void setProperty(String name, String value) {
        this.properties.put(name, value);
    }

    public String getProperty(String name) {
        return properties.get(name);
    }

    public void addChild(XMLEntry entry) {
        children.add(entry);
    }

    public Set<XMLEntry> getChildrenByName(String name) {
        HashSet<XMLEntry> set = new HashSet<XMLEntry>();
        for (XMLEntry entry : children)
            if (entry.getName().equals(name))
                set.add(entry);
        return set;
    }

    public HashMap<String, String> getProperties() {
        return properties;
    }

    public ArrayList<XMLEntry> children() {
        return children;
    }

}
