package de.mineformers.gui.minecraft;

import de.mineformers.gui.component.canvas.UICanvasContainer;
import net.minecraft.client.gui.inventory.GuiContainer;
import org.lwjgl.opengl.GL11;

/**
 * GUISystem
 * <p/>
 * GuiContainerGUISystem
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class WidgetGuiContainer extends GuiContainer {

    private int canvasWidth, canvasHeight;
    private UICanvasContainer canvas;

    public WidgetGuiContainer(int width, int height, UICanvasContainer canvas) {
        super(canvas.getContainer());
        this.canvas = canvas;
        this.canvasWidth = width;
        this.canvasHeight = height;
        this.xSize = width;
        this.ySize = height;
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int button) {
        super.mouseClicked(mouseX, mouseY, button);
        canvas.mouseClick(mouseX, mouseY, button);
    }

    @Override
    protected void keyTyped(char keyChar, int keyCode) {
        super.keyTyped(keyChar, keyCode);
        canvas.keyType(keyChar, keyCode);
    }

    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        int xStart = (width - canvasWidth) / 2;
        int yStart = (height - canvasHeight) / 2;

        canvas.setPos(xStart, yStart);
        canvas.drawForeground(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int mouseX,
                                                   int mouseY) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        int xStart = (width - canvasWidth) / 2;
        int yStart = (height - canvasHeight) / 2;

        canvas.setPos(xStart, yStart);
        canvas.draw(mouseX, mouseY);
    }

    @Override
    public void drawScreen(int par1, int par2, float par3) {
        super.drawScreen(par1, par2, par3);
    }

}
