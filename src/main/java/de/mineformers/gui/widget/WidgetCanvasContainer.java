package de.mineformers.gui.widget;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

import org.lwjgl.opengl.GL11;

import de.mineformers.gui.util.LangHelper;

/**
 * Kybology
 * 
 * WidgetCanvasContainer
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class WidgetCanvasContainer extends WidgetCanvas {

	private boolean autoDrawSlots;
	private Container container;
	private String name;

	public WidgetCanvasContainer(int x, int y, Container container,
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

		for (Widget widget : widgets) {
			if (widget.isVisible()) {
				GL11.glPushMatrix();
				GL11.glTranslatef(x, y, 0);
				widget.setScreenPos(widget.getX() + x, widget.getY() + y);
				widget.draw(mouseX, mouseY);
				GL11.glPopMatrix();
			}
		}

		if (autoDrawSlots) {
			WidgetSlot widget = new WidgetSlot(0, 0, 18, 18);

			for (Object o : container.inventorySlots) {
				if (o instanceof Slot) {
					Slot slot = (Slot) o;
					if (!(slot.inventory instanceof InventoryPlayer)) {
						widget.setPos(x + slot.xDisplayPosition - 1, y
						        + slot.yDisplayPosition - 1);
						widget.draw(mouseX, mouseY);
					}
				}
			}
		}
	}

	public Container getContainer() {
		return container;
	}

}
