package de.mineformers.gui.component.inventory;

import de.mineformers.gui.component.UIComponent;
import de.mineformers.gui.system.Global;
import de.mineformers.gui.util.RenderHelper;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.Icon;
import net.minecraftforge.fluids.FluidStack;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Color;

/**
 * GUISystem
 * <p/>
 * UITank
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class UITank extends UIComponent {

    private int maxAmount;
    private UISlot slot;
    private boolean drawSlot;
    private FluidStack fluid;

    public UITank(int width, int height, FluidStack fluid) {
        super(Global.getTexture());
        this.fluid = fluid;
        this.width = width;
        this.height = height;
        this.maxAmount = 8000;
        slot = new UISlot(width, height);
        this.updateTooltip();
    }

    public void setFluid(FluidStack fluid) {
        this.fluid = fluid;
        this.updateTooltip();
    }

    public void setDrawSlot(boolean drawSlot) {
        this.drawSlot = drawSlot;
    }

    public void update(int mouseX, int mouseY) {
    }

    public void setMaxAmount(int maxAmount) {
        this.maxAmount = maxAmount;
        this.updateTooltip();
    }

    private void updateTooltip() {
        this.setTooltip(fluid.amount + "mB/" + maxAmount + "mB\n" + fluid.getFluid().getLocalizedName());
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    public void setFluidAmount(int amount) {
        fluid.amount = amount;
        this.updateTooltip();
    }

    public int mapAmountOnHeight(int height) {
        return fluid.amount * height / maxAmount;
    }

    @Override
    public boolean isHovered(int mouseX, int mouseY) {
        return this.isInsideRegion(mouseX, mouseY, screenX, screenY, screenX + width, screenY + height);
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        Icon icon = fluid.getFluid().getIcon(fluid);

        Color rgb = RenderHelper.getRGBFromColor(fluid.getFluid().getColor(fluid));
        float red = (1F / 255F) * rgb.getRed();
        float green = (1F / 255F) * rgb.getGreen();
        float blue = (1F / 255F) * rgb.getBlue();

        if (drawSlot) {
            int drawHeight = mapAmountOnHeight(height - 2);
            slot.setScreenPos(screenX, screenY);
            slot.draw(mouseX, mouseY);
            GL11.glColor4f(red, green, blue, 1);
            this.drawRectangleRepeated(TextureMap.locationBlocksTexture, screenX + 1, screenY - 1 + height - drawHeight, icon.getMinU(), icon.getMinV(), icon.getMaxU() - icon.getMinU(), icon.getMaxV() - icon.getMinV(), width - 2, drawHeight, 24, 24);
        } else {
            int drawHeight = mapAmountOnHeight(height);
            GL11.glColor4f(red, green, blue, 1);
            this.drawRectangleRepeated(TextureMap.locationBlocksTexture, screenX, screenY + height - drawHeight, icon.getMinU(), icon.getMinV(), icon.getMaxU() - icon.getMinU(), icon.getMaxV() - icon.getMinV(), width, drawHeight, 24, 24);
        }
    }


}
