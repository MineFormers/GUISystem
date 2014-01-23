package de.mineformers.gui.api.event;

import de.mineformers.gui.api.component.UIComponent;

/**
 * GUISystem
 * <p/>
 * KeyTypedEvent
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class KeyTypedEvent extends Event
{

    public char keyChar;
    public int keyCode;

    public KeyTypedEvent(UIComponent component, char keyChar, int keyCode)
    {
        super(component);
        this.keyChar = keyChar;
        this.keyCode = keyCode;
    }

}
