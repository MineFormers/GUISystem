package de.mineformers.gui.component.container;

import de.mineformers.gui.component.UIComponent;
import de.mineformers.gui.component.layout.UILayout;
import de.mineformers.gui.system.Global;

/**
 * GUISystem
 * <p/>
 * UIPanel
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class UIPanel extends UIComponent {

    protected UILayout layout;

    public UIPanel() {
        super(Global.getTexture());
    }

    public void setLayout(UILayout layout) {
        this.layout = layout;
    }

    public UILayout getLayout() {
        return layout;
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        layout.setScreenPos(screenX, screenY);
        layout.draw(mouseX, mouseY);
    }

    @Override
    public int getWidth() {
        return layout.getWidth();
    }

    @Override
    public int getHeight() {
        return layout.getHeight();
    }

    public void mouseClick(int mouseX, int mouseY, int mouseButton) {
        layout.mouseClick(mouseX, mouseY, mouseButton);
    }

    public void keyTyped(char keyChar, int keyCode) {
        layout.keyTyped(keyChar, keyCode);
    }
}
