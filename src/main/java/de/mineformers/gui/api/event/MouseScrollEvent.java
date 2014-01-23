package de.mineformers.gui.api.event;

import de.mineformers.gui.api.component.UIComponent;

/**
 * GUISystem
 * <p/>
 * MouseScrollEvent
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class MouseScrollEvent extends Event
{

    public int dir;
    public int mouseX;
    public int mouseY;

    public MouseScrollEvent(UIComponent component, int dir, int mouseX, int mouseY)
    {
        super(component);
        this.dir = dir;
        this.mouseX = mouseX;
        this.mouseY = mouseY;
    }

}
