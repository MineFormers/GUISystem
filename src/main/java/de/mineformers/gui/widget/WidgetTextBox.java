package de.mineformers.gui.widget;

import net.minecraft.util.ChatAllowedCharacters;

import org.lwjgl.input.Keyboard;

import de.mineformers.gui.system.Global;
import de.mineformers.gui.widget.listener.ListenerClickable;
import de.mineformers.gui.widget.listener.ListenerKeyboard;

/**
 * Kybology
 * 
 * WidgetTextBox
 * 
 * WARNING: Don't use me yet, I'm very WIP! (I'm working, but my graphics are
 * just ugly)
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class WidgetTextBox extends Widget implements ListenerClickable,
		ListenerKeyboard {

	private int width, height;
	private String text;

	private WidgetSlot slotWidget;
	private boolean useSlotBg;
	private boolean focused;
	private int cursorPos;
	private int blinkTick;

	public WidgetTextBox(int x, int y, int width, int height, String startText,
			boolean useSlotBg) {
		super(Global.getTexture(), x, y);
		this.width = width;
		this.height = height;
		this.text = startText;
		this.slotWidget = new WidgetSlot(x, y, width, height);
		this.useSlotBg = useSlotBg;
		this.addListener(this);
		this.focused = false;
		this.cursorPos = startText.length() - 1;
	}

	@Override
	public void draw(int mouseX, int mouseY) {

		if (!useSlotBg) {
			// Corners clockwise
			this.drawRectangle(x, y, 31, 16, 5, 5);
			this.drawRectangle(x + width - 5, y, 39, 16, 5, 5);
			this.drawRectangle(x + width - 5, y + height - 5, 39, 24, 5, 5);
			this.drawRectangle(x, y + height - 5, 31, 24, 5, 5);

			// Sides clockwise
			this.drawRectangleStretched(x + 5, y, 37, 16, width - 10, 5, 1, 5);
			this.drawRectangleStretched(x + width - 5, y + 5, 39, 22, 5,
					height - 10, 5, 1);
			this.drawRectangleStretched(x + 5, y + height - 5, 37, 24,
					width - 10, 5, 1, 5);
			this.drawRectangleStretched(x, y + 5, 31, 22, 5, height - 10, 5, 1);

			// Canvas
			this.drawRectangleStretched(x + 5, y + 5, 37, 22, width - 10,
					height - 10, 1, 1);
		} else {
			slotWidget.setPos(x, y);
			slotWidget.draw(mouseX, mouseY);
		}

		String toDraw = text;
		int sWidth = this.getStringWidth(toDraw);
		sWidth += focused ? this.getStringWidth("_") : 0;
		if (focused) {
			for (int i = 0; i <= cursorPos && sWidth > width - 4; i++) {
				toDraw = text.substring(i, cursorPos);
				sWidth = this.getStringWidth(toDraw) + this.getStringWidth("_");
			}
		} else {
			for (int i = 0; i < text.length() && sWidth > width - 4; i++) {
				toDraw = text.substring(0, text.length() - i);
				sWidth = this.getStringWidth(toDraw);
			}
		}

		this.drawString(toDraw, x + 2, y + (height - 8) / 2, 0xe0e0e0, true);
		if (focused) {
			if (blinkTick >= 30) {
				this.drawString("_", x + 2 + sWidth - this.getStringWidth("_"),
						y + (height - 8) / 2 + 2, 0xe0e0e0, true);
			}

			if (blinkTick >= 60) {
				blinkTick = 0;
			}
			blinkTick++;
		}

	}

	@Override
	public boolean isHovered(int mouseX, int mouseY) {
		return true;
	}

	@Override
	public void onClick(int mouseX, int mouseY) {
		if (mouseX > screenX && mouseY > screenY && mouseX < (screenX + width)
				&& mouseY < (screenY + height)) {
			this.focused = true;
		} else {
			this.focused = false;
		}
	}

	@Override
	public void onKeyTyped(char keyChar, int keyCode) {
		if (focused) {
			switch (keyCode) {
				case Keyboard.KEY_LEFT:
					this.cursorPos -= 1;
					if (cursorPos < 0)
						cursorPos = 0;
					break;
				case Keyboard.KEY_RIGHT:
					this.cursorPos += 1;
					if (cursorPos > text.length() - 1)
						cursorPos = text.length() - 1;
					break;
				case Keyboard.KEY_BACK:
					if (this.text.length() > 0) {
						if (this.text.length() > 1)
							this.text = this.text.substring(0, cursorPos - 1)
									+ this.text.substring(cursorPos);
						else
							this.text = "";
						cursorPos -= 1;
						if (cursorPos < 0)
							cursorPos = 0;
					}
					break;
				default:
					if (ChatAllowedCharacters.isAllowedCharacter(keyChar)) {
						this.text = text.substring(0, cursorPos)
								+ Character.toString(keyChar)
								+ text.substring(cursorPos);
						this.cursorPos += 1;
					}
					break;
			}
		}
	}

}
