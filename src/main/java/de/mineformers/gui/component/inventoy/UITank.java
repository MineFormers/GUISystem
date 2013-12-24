package de.mineformers.gui.component.inventoy;

import de.mineformers.gui.component.UIComponent;
import de.mineformers.gui.component.decorative.UITooltip;
import de.mineformers.gui.system.Global;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.Icon;
import net.minecraftforge.fluids.FluidStack;

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
    private UITooltip tooltip;

    public UITank(int width, int height, FluidStack fluid) {
        super(Global.getTexture());
        this.fluid = fluid;
        this.width = width;
        this.height = height;
        this.maxAmount = 8000;
        slot = new UISlot(width, height);
        tooltip = new UITooltip();
        tooltip.addLine(fluid.amount + "mB/" + maxAmount + "mB");
        tooltip.addLine(fluid.getFluid().getLocalizedName());
    }

    public void setFluid(FluidStack fluid) {
        this.fluid = fluid;
        tooltip.reset();
        tooltip.addLine(fluid.amount + "mB/" + maxAmount + "mB");
        tooltip.addLine(fluid.getFluid().getLocalizedName());
    }

    public void setDrawSlot(boolean drawSlot) {
        this.drawSlot = drawSlot;
    }

    public void update(int mouseX, int mouseY) {
        tooltip.update(mouseX, mouseY);
    }

    public void setMaxAmount(int maxAmount) {
        this.maxAmount = maxAmount;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    public void setFluidAmount(int amount) {
        fluid.amount = amount;
    }

    public int mapAmountOnHeight(int height) {
        return fluid.amount * height / maxAmount;
    }

    @Override
    public boolean isHovered(int mouseX, int mouseY) {
        return this.isInsideRegion(mouseX, mouseY, screenX, screenY, screenX + width, screenY + height);
    }
    
    @Override
    public void drawForeground(int mouseX, int mouseY) {
    	super.drawForeground(mouseX, mouseY);


        if (isHovered(mouseX, mouseY))
            tooltip.draw(mouseX, mouseY);
    }
    
    @Override
    public void draw(int mouseX, int mouseY) {
        Icon icon = fluid.getFluid().getIcon(fluid);
        
        if (drawSlot) {
            int drawHeight = mapAmountOnHeight(height - 2);
            slot.setScreenPos(screenX, screenY);
            slot.draw(mouseX, mouseY);
            this.drawRectangleRepeated(TextureMap.locationBlocksTexture, screenX + 1, screenY - 1 + height - drawHeight, icon.getMinU(), icon.getMinV(), icon.getMaxU() - icon.getMinU(), icon.getMaxV() - icon.getMinV(), width - 2, drawHeight, 24, 24);
        } else {
            int drawHeight = mapAmountOnHeight(height);
            this.drawRectangleRepeated(TextureMap.locationBlocksTexture, screenX, screenY + height - drawHeight, icon.getMinU(), icon.getMinV(), icon.getMaxU() - icon.getMinU(), icon.getMaxV() - icon.getMinV(), width, drawHeight, 24, 24);
        }
    }


}
