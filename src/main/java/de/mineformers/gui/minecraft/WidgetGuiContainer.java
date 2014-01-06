package de.mineformers.gui.minecraft;

import de.mineformers.gui.component.container.UIPanel;
import de.mineformers.gui.component.inventory.UISlot;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import org.lwjgl.input.Mouse;
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

    private UIPanel panel;
    protected String name;
    protected boolean autoDrawSlots;

    public WidgetGuiContainer(int width, int height, UIPanel panel, Container container) {
        this(width, height, panel, container, null);
    }

    public WidgetGuiContainer(int width, int height, UIPanel panel, Container container, IInventory inventory) {
        this(width, height, panel, container, inventory, false);
    }

    public WidgetGuiContainer(int width, int height, UIPanel panel, Container container, IInventory inventory, boolean autoDrawSlots) {
        super(container);
        this.panel = panel;
        this.panel.setSize(width, height);
        this.field_146294_l = width;
        this.field_146295_m = height;
        if (inventory != null)
            this.name = inventory.func_145825_b();
        this.autoDrawSlots = autoDrawSlots;
    }

    public UIPanel getPanel() {
        return panel;
    }

    @Override
    public void initGui() {
        super.initGui();

        this.panel.initComponent();
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int button) {
        super.mouseClicked(mouseX, mouseY, button);
        panel.mouseClick(mouseX, mouseY, button);
    }

    @Override
    protected void keyTyped(char keyChar, int keyCode) {
        super.keyTyped(keyChar, keyCode);
        panel.keyTyped(keyChar, keyCode);
    }

    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    protected void func_146976_a(float var1, int mouseX, int mouseY) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        int xStart = (field_146294_l - panel.getWidth()) / 2;
        int yStart = (field_146295_m - panel.getHeight()) / 2;

        panel.setScreenPos(xStart, yStart);

        RenderHelper.enableGUIStandardItemLighting();
        panel.drawBackground(mouseX, mouseY);
        panel.draw(mouseX, mouseY);

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        panel.drawForeground(mouseX, mouseY);
        if (name != null)
            de.mineformers.gui.util.RenderHelper.drawString(name, xStart + 5, yStart + 5, 0x404040, false, 1);
        if (autoDrawSlots) {
            UISlot widget = new UISlot().init("width", 18, "height", 18);

            for (Object o : field_147002_h.inventorySlots) {
                if (o instanceof Slot) {

                    Slot slot = (Slot) o;

                    GL11.glPushMatrix();
                    widget.setScreenPos(field_147003_i + slot.xDisplayPosition - 1, field_147009_r
                            + slot.yDisplayPosition - 1);
                    widget.draw(mouseX, mouseY);
                    GL11.glPopMatrix();
                }
            }
        }
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        ScaledResolution scaledresolution = new ScaledResolution(this.field_146297_k.gameSettings, this.field_146297_k.displayWidth, this.field_146297_k.displayHeight);
        int i = scaledresolution.getScaledWidth();
        int j = scaledresolution.getScaledHeight();
        int k = Mouse.getX() * i / this.field_146297_k.displayWidth;
        int l = j - Mouse.getY() * j / this.field_146297_k.displayHeight - 1;
        this.panel.update(k, l);
        int dWheel = Mouse.getDWheel() / 120;

        if (dWheel != 0) {
            panel.mouseScroll(-dWheel, k, l);
        }
    }
}
