package de.mineformers.gui.component.canvas;

import de.mineformers.gui.component.inventoy.UISlot;
import de.mineformers.gui.util.LangHelper;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import org.lwjgl.opengl.GL11;

/**
 * GUISystem
 * <p/>
 * GuiCanvasContainer
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class UICanvasContainer extends UICanvas {

    private boolean autoDrawSlots;
    private Container container;
    private String name;

    public UICanvasContainer(int x, int y, Container container,
                             IInventory inventory, boolean autoDrawSlots) {
        super(x, y);
        this.container = container;
        this.name = inventory.isInvNameLocalized() ? inventory.getInvName()
                : LangHelper
                .translate("container", "furnace");
        this.autoDrawSlots = autoDrawSlots;
    }

    @Override
    public void drawForeground(int mouseX, int mouseY) {
        this.drawString(name, 5, 5, 0x404040, false);
        GL11.glColor4f(1, 1, 1, 1);
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        this.drawBackground(mouseX, mouseY);

        GL11.glPushMatrix();
        GL11.glTranslatef(x, y, 0);
        panel.setScreenPos(x, y);
        panel.draw(mouseX, mouseY);
        GL11.glPopMatrix();

        if (autoDrawSlots) {
            UISlot widget = new UISlot(18, 18);

            for (Object o : container.inventorySlots) {
                if (o instanceof Slot) {
                    Slot slot = (Slot) o;
                    if (!(slot.inventory instanceof InventoryPlayer)) {
                        GL11.glPushMatrix();
                        GL11.glTranslatef(x + slot.xDisplayPosition - 1, y
                                + slot.yDisplayPosition - 1, 0);
                        widget.draw(mouseX, mouseY);
                        GL11.glPopMatrix();
                    }
                }
            }
        }
    }

    public Container getContainer() {
        return container;
    }

}
