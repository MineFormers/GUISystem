package de.mineformers.gui.component.container;

import de.mineformers.gui.component.interaction.UIScrollBar;
import de.mineformers.gui.component.layout.UIAbsoluteLayout;
import de.mineformers.gui.util.PropertyHelper;

public class UIPanelScrollable extends UIPanel {
    public UIScrollBar scrollBar;

    @Override
    public void init(PropertyHelper properties) {
        super.init(properties);
        scrollBar = new UIScrollBar().init("x", 6, "y", height - 2);
        UIAbsoluteLayout layout = new UIAbsoluteLayout();
        layout.addComponent(scrollBar, (screenX + this.width) - this.scrollBar.getWidth() - 1, screenY + 1);
        setLayout(layout);
    }

    @Override
    public boolean isHovered(int mouseX, int mouseY) {
        return true;
    }

    @Override
    public void mouseClick(int mouseX, int mouseY, int mouseButton) {
        super.mouseClick(mouseX, mouseY, mouseButton);

    }

    @Override
    public void mouseScroll(int dir, int mouseX, int mouseY) {
        super.mouseScroll(dir, mouseX, mouseY);

    }

    @Override
    public void keyTyped(char keyChar, int keyCode) {
        super.keyTyped(keyChar, keyCode);

    }

    @Override
    public int getWidth() {
        return this.width - this.scrollBar.getWidth();
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public void update(int mouseX, int mouseY) {
        super.update(mouseX, mouseY);
    }

    @Override
    public void draw(int mouseX, int mouseY) {

		/*GL11.glPushAttrib(GL11.GL_SCISSOR_BIT);
        GL11.glEnable(GL11.GL_SCISSOR_TEST);
		
		int scale = RenderHelper.computeGuiScale();
		glScissor((screenX + 1) * scale, mc.displayHeight - (screenY + height - 1) * scale, (width - 2) * scale, (height - 2) * scale);
		
		GL11.glDisable(GL11.GL_SCISSOR_TEST);
		GL11.glPopAttrib();*/

        super.draw(mouseX, mouseY);
    }
}
