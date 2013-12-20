package de.mineformers.gui.widget;

import org.lwjgl.opengl.GL11;

import de.mineformers.gui.system.Global;
import de.mineformers.gui.widget.listener.ListenerClickable;

/**
 * Kybology
 * 
 * WidgetButton
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class WidgetButton extends Widget implements ListenerClickable {

	protected boolean enabled;

	protected int width, height;
	protected String text;

	public WidgetButton(int x, int y, int width, int height, String text) {
		super(Global.getTexture(), x, y);
		this.width = width;
		this.height = height;
		this.text = text;
		this.enabled = true;
		this.addListener(this);
	}

	@Override
	public void draw(int mouseX, int mouseY) {
		boolean hovering = isHovered(mouseX, mouseY);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		int state = getHoverState(hovering);

		// Corners clockwise
		this.drawRectangle(x, y, 1 + 19 * state, 31, 5, 5);
		this.drawRectangle(x + width - 5, y, 13 + 19 * state, 31, 5, 5);
		this.drawRectangle(x + width - 5, y + height - 5, 13 + 19 * state, 43,
		        5, 5);
		this.drawRectangle(x, y + height - 5, 1 + 19 * state, 43, 5, 5);

		// Sides clockwise
		this.drawRectangleStretched(x + 5, y, 7 + 19 * state, 31, width - 10,
		        5, 5, 5);
		this.drawRectangleStretched(x + width - 5, y + 5, 13 + 19 * state, 37,
		        5, height - 10, 5, 5);
		this.drawRectangleStretched(x + 5, y + height - 5, 7 + 19 * state, 43,
		        width - 10, 5, 5, 5);
		this.drawRectangleStretched(x, y + 5, 1 + 19 * state, 37, 5,
		        height - 10, 5, 5);

		// Canvas
		this.drawRectangleStretched(x + 5, y + 5, 7 + 19 * state, 37,
		        width - 10, height - 10, 5, 5);

		int color = 0xe0e0e0;

		if (!this.enabled) {
			color = -0x5f5f60;
		} else if (hovering) {
			color = 0xffffa0;
		}

		this.drawString(text,
		        x + ((width - this.getStringWidth(text)) / 2), y
		                + ((height - 8) / 2), color, true);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}

	protected int getHoverState(boolean hovering) {
		byte b0 = 1;

		if (!this.enabled) {
			b0 = 0;
		} else if (hovering) {
			b0 = 2;
		}

		return b0;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public boolean isHovered(int mouseX, int mouseY) {
		return mouseX > screenX && mouseY > screenY
		        && mouseX < (screenX + width) && mouseY < (screenY + height);
	}

	@Override
	public void onClick(int mouseX, int mouseY) {
		mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
	}

}
