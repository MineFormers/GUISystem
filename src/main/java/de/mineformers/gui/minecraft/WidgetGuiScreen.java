package de.mineformers.gui.minecraft;

import de.mineformers.gui.component.canvas.UICanvas;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

/**
 * GUISystem
 * <p/>
 * GuiScreenTT
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class WidgetGuiScreen extends GuiScreen {

    private int canvasWidth, canvasHeight;
    private UICanvas canvas;

    public WidgetGuiScreen(int width, int height, UICanvas canvas) {
        this.mc = Minecraft.getMinecraft();
        this.width = width;
        this.height = height;
        this.canvas = canvas;
        this.canvasWidth = width;
        this.canvasHeight = height;
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int button) {
        canvas.mouseClick(mouseX, mouseY, button);
    }

    @Override
    protected void keyTyped(char keyChar, int keyCode) {
        super.keyTyped(keyChar, keyCode);
        canvas.keyType(keyChar, keyCode);
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        ScaledResolution scaledresolution = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
        int i = scaledresolution.getScaledWidth();
        int j = scaledresolution.getScaledHeight();
        int k = Mouse.getX() * i / this.mc.displayWidth;
        int l = j - Mouse.getY() * j / this.mc.displayHeight - 1;
        this.canvas.update(k, l);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float opacity) {
        this.drawDefaultBackground();

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        int xStart = (width - canvasWidth) / 2;
        int yStart = (height - canvasHeight) / 2;

        canvas.setPos(xStart, yStart);
        canvas.draw(mouseX, mouseY);
    }

    public boolean doesGuiPauseGame() {
        return false;
    }

}
