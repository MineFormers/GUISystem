package de.mineformers.gui.widget;

import org.lwjgl.opengl.GL11;

import de.mineformers.gui.system.Global;
import de.mineformers.gui.util.LangHelper;

/**
 * Kybology
 * 
 * WidgetInventoryPlayer
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class WidgetInventoryPlayer extends Widget {

	private WidgetInventory widget;

	public WidgetInventoryPlayer(int x, int y) {
		super(Global.getTexture(), x, y);
		this.widget = new WidgetInventory(0, 0, 9, 3);
	}

	@Override
	public void draw(int mouseX, int mouseY) {
		this.drawString(LangHelper.translate("container.inventory"), x, y,
				0x404040, false);
		GL11.glColor4f(1, 1, 1, 1);
		widget.setPos(x, y + 10);
		widget.setSlots(9, 3);
		widget.draw(mouseX, mouseY);

		widget.setPos(x, y + 68);
		widget.setSlots(9, 1);
		widget.draw(mouseX, mouseY);
	}

}
