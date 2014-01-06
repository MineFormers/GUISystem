package de.mineformers.gui.component.interaction;

import de.mineformers.gui.util.PropertyHelper;
import org.lwjgl.opengl.GL11;

/**
 * GUISystem
 * <p/>
 * WidgetButtonPage
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class UINavigationButton extends UIButton {

    public static final int TYPE_NEXT = 0;
    public static final int TYPE_PREV = 1;

    private int buttonType;

    @Override
    public void init(PropertyHelper properties) {
        super.init(properties);
        this.text = "";
        this.width = 10;
        this.height = 15;
        this.buttonType = properties.get("type", TYPE_NEXT);
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        boolean hovering = isHovered(mouseX, mouseY);
        int state = getHoverState(hovering);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        this.drawRectangle(screenX, screenY, state * 10, buttonType * 15, 10, 15);
    }

    protected int getHoverState(boolean hovering) {
        byte b0 = 0;

        if (!this.enabled) {
            b0 = 2;
        } else if (hovering) {
            b0 = 1;
        }

        return b0;
    }

}
