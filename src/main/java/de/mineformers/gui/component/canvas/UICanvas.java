package de.mineformers.gui.component.canvas;

import de.mineformers.gui.component.UIComponent;
import de.mineformers.gui.component.container.UIPanel;
import de.mineformers.gui.system.Global;
import org.lwjgl.opengl.GL11;

/**
 * GUISystem
 * <p/>
 * WidgetCanvas
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class UICanvas extends UIComponent {

    protected UIPanel panel;
    protected int x, y;

    public UICanvas(int x, int y) {
        super(Global.getTexture());
        this.x = x;
        this.y = y;
    }

    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public UIPanel getPanel() {
        return panel;
    }

    public void setPanel(UIPanel panel) {
        this.panel = panel;
    }

    public void drawForeground(int mouseX, int mouseY) {
    }

    public void drawBackground(int mouseX, int mouseY) {
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        drawBackground(mouseX, mouseY);
        GL11.glPushMatrix();
        if (panel != null) {
            panel.setScreenPos(x, y);
            panel.draw(mouseX, mouseY);
        }
        GL11.glPopMatrix();
        drawForeground(mouseX, mouseY);
    }

    public void mouseClick(int mouseX, int mouseY, int mouseButton) {
        panel.mouseClick(mouseX, mouseY, mouseButton);
    }

    public void keyType(char keyChar, int keyCode) {
        panel.keyTyped(keyChar, keyCode);
    }

}
