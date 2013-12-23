package de.mineformers.gui.component.inventoy;

import de.mineformers.gui.component.UIComponent;
import de.mineformers.gui.system.Global;
import de.mineformers.gui.util.RenderHelper;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.Icon;
import net.minecraftforge.fluids.FluidStack;
import org.lwjgl.opengl.GL11;

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
    private FluidStack fluid;

    public UITank(int width, int height, FluidStack fluid) {
        super(Global.getTexture());
        this.fluid = fluid;
        this.width = width;
        this.height = height;
        this.maxAmount = 8000;
    }

    public void update(int mouseX, int mouseY) {

    }

    public void setMaxAmount(int maxAmount) {
        this.maxAmount = maxAmount;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    public int mapAmountOnHeight() {
        return fluid.amount * height / maxAmount;
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        Icon icon = fluid.getFluid().getIcon(fluid);
        int drawHeight = mapAmountOnHeight();
        int scale = RenderHelper.computeGuiScale();

        GL11.glScissor(screenX * scale, mc.displayHeight - (screenY + height) * scale, width * scale, drawHeight * scale);
        GL11.glEnable(GL11.GL_SCISSOR_TEST);
        int cols = (int) Math.ceil((double) width / 32);
        int rows = (int) Math.ceil((double) drawHeight / 32);
        for (int col = 0; col <= cols; col++) {
            for (int row = 0; row <= rows; row++) {
                this.drawRectangleStretched(TextureMap.locationBlocksTexture, screenX + col * 32, screenY + height - drawHeight + row * 32,
                        icon.getMinU(), icon.getMinV(), 32, 32,
                        icon.getMaxU(), icon.getMaxV(), true);
            }
        }
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glColor4f(1, 0, 0, 1);
        this.drawRectangle(0, 0, 0, 0, mc.displayWidth, mc.displayHeight);
        GL11.glColor4f(1, 1, 1, 1);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_SCISSOR_TEST);
    }


}
