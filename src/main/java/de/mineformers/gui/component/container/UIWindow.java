package de.mineformers.gui.component.container;


import de.mineformers.gui.util.Padding;
import de.mineformers.gui.util.RenderHelper;
import org.lwjgl.opengl.GL11;

/**
 * GUISystem
 * <p/>
 * WidgetWindow
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class UIWindow extends UIPanel {

    private Padding padding;

    public UIWindow(int width, int height) {
        super();
        this.width = width;
        this.height = height;
        this.padding = Padding.ALL5;
    }

    public void setPadding(Padding padding) {
        this.padding = padding;
    }

    public Padding getPadding() {
        return padding;
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        this.drawRectangle(screenX, screenY, 31, 1, 5, 5);
        this.drawRectangle(screenX + width - 5, screenY, 39, 1, 5, 5);
        this.drawRectangle(screenX + width - 5, screenY + height - 5, 39, 9, 5, 5);
        this.drawRectangle(screenX, screenY + height - 5, 31, 9, 5, 5);

        // Sides clockwise
        this.drawRectangleStretched(screenX + 5, screenY, 37, 1, width - 10, 5, 1, 5);
        this.drawRectangleStretched(screenX + width - 5, screenY + 5, 39, 7, 5,
                height - 10, 5, 1);
        this.drawRectangleStretched(screenX + 5, screenY + height - 5, 37, 9, width - 10,
                5, 1, 5);
        this.drawRectangleStretched(screenX, screenY + 5, 31, 7, 5, height - 10, 5, 1);

        // Canvas
        this.drawRectangleStretched(screenX + 5, screenY + 5, 37, 7, width - 10,
                height - 10, 1, 1);

        int scale = RenderHelper.computeGuiScale();

        GL11.glScissor(0, mc.displayHeight - (screenY + height) * scale, (width - padding.right + screenX) * scale, (height - padding.bottom) * scale);
        GL11.glEnable(GL11.GL_SCISSOR_TEST);
        layout.setScreenPos(screenX + padding.left, screenY + padding.top);
        layout.draw(mouseX, mouseY);
        GL11.glDisable(GL11.GL_SCISSOR_TEST);
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

}
