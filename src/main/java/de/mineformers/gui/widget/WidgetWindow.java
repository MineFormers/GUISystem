package de.mineformers.gui.widget;

import de.mineformers.gui.system.Global;

/**
 * Kybology
 * 
 * WidgetWindow
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class WidgetWindow extends Widget {

	private int width, height;

	public WidgetWindow(int x, int y, int width, int height) {
		super(Global.getTexture(), x, y);
		this.width = width;
		this.height = height;
	}

	@Override
	public void draw(int mouseX, int mouseY) {
		// Corners clockwise
		this.drawRectangle(x, y, 31, 1, 5, 5);
		this.drawRectangle(x + width - 5, y, 39, 1, 5, 5);
		this.drawRectangle(x + width - 5, y + height - 5, 39, 9, 5, 5);
		this.drawRectangle(x, y + height - 5, 31, 9, 5, 5);

		// Sides clockwise
		this.drawRectangleStretched(x + 5, y, 37, 1, width - 10, 5, 1, 5);
		this.drawRectangleStretched(x + width - 5, y + 5, 39, 7, 5,
				height - 10, 5, 1);
		this.drawRectangleStretched(x + 5, y + height - 5, 37, 9, width - 10,
				5, 1, 5);
		this.drawRectangleStretched(x, y + 5, 31, 7, 5, height - 10, 5, 1);

		// Canvas
		this.drawRectangleStretched(x + 5, y + 5, 37, 7, width - 10,
				height - 10, 1, 1);
	}

}
