package de.mineformers.gui.component.inventory;

import de.mineformers.gui.component.UIComponent;
import de.mineformers.gui.util.PropertyHelper;

/**
 * GUISystem
 * <p/>
 * WidgetInventory
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class UIInventory extends UIComponent {

    private UISlot slot;

    @Override
    public void init(PropertyHelper properties) {
        slot = new UISlot().init("width", 18, "height", 18);
        this.width = properties.get("slotsX", 1);
        this.height = properties.get("slotsY", 1);
    }

    @Override
    public void update(int mouseX, int mouseY) {

    }

    public void draw(int mouseX, int mouseY) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                slot.setScreenPos(screenX + i * 18, screenY + j * 18);
                slot.draw(mouseX, mouseY);
            }
        }
    }

    public void setSlots(int slotsX, int slotsY) {
        this.width = slotsX;
        this.height = slotsY;
    }

    @Override
    public boolean isHovered(int mouseX, int mouseY) {
        return false;
    }

    @Override
    public int getWidth() {
        return width * 18;
    }

    @Override
    public int getHeight() {
        return height = 18;
    }
}
