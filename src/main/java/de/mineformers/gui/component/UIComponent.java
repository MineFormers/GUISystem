package de.mineformers.gui.component;

import com.google.common.primitives.Primitives;
import cpw.mods.fml.client.FMLClientHandler;
import de.mineformers.gui.listener.Listener;
import de.mineformers.gui.util.RenderHelper;
import de.mineformers.gui.util.TextHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Color;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * GUISystem
 * <p/>
 * GuiComponent
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class UIComponent {

    protected int screenX, screenY;
    protected Minecraft mc;
    protected ResourceLocation texture;
    private int zLevel;
    protected boolean visible;
    protected int width, height;

    private ArrayList<Listener> listeners;

    public UIComponent(ResourceLocation texture) {
        this.mc = FMLClientHandler.instance().getClient();
        this.texture = texture;
        this.zLevel = 0;
        listeners = new ArrayList<Listener>();
        this.visible = true;
    }

    public int getStringWidth(String text) {
        return mc.fontRenderer.getStringWidth(text);
    }

    public void drawString(String text, int x, int y, int color,
                           boolean drawShadow) {
        this.mc.fontRenderer.drawString(text, x, y, color, drawShadow);
    }

    public void drawSplitString(String text, int x, int y, int color,
                                boolean drawShadow) {
        String[] splits = text.split("<br>");
        for (int i = 0; i < splits.length; i++) {
            this.mc.fontRenderer.drawString(splits[i], x, y + i * 10, color,
                    drawShadow);
        }
    }

    public void drawSplitStringCentered(String text, int x, int y, int color,
                                        boolean drawShadow, int canvasWidth) {
        String[] splits = text.split("<br>");
        int longest = mc.fontRenderer.getStringWidth(TextHelper
                .getLongestString(splits));
        for (int i = 0; i < splits.length; i++) {
            this.mc.fontRenderer.drawString(
                    splits[i],
                    x
                            + ((canvasWidth - longest) / 2)
                            + ((longest - mc.fontRenderer
                            .getStringWidth(splits[i])) / 2), y + i
                    * 10, color, drawShadow);
        }
    }

    public void drawRectangle(Color color, int x, int y, int width, int height) {
        float colorMod = 1F / 255F;
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(colorMod * color.getRed(), colorMod * color.getGreen(), colorMod * color.getBlue(), colorMod * color.getAlpha());
        this.drawRectangle(x, y, 0, 0, width, height);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glColor4f(1F, 1F, 1F, 1F);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }

    public void drawRectangle(int color, int x, int y, int width, int height) {
        drawRectangle(color, 1F, x, y, width, height);
    }

    public void drawRectangle(int color, float alpha, int x, int y, int width, int height) {
        Color rgb = RenderHelper.getRGBFromColor(color);
        rgb.setAlpha((int) (alpha * 255));
        drawRectangle(rgb, x, y, width, height);
    }

    public void drawRectangle(int x, int y, int u, int v, int width, int height) {
        drawRectangle(texture, x, y, u, v, width, height);
    }

    public void drawRectangle(ResourceLocation texture, int x, int y, float u,
                              float v, int width, int height) {
        RenderHelper.bindTexture(texture);
        float f = 0.00390625F;
        float f1 = 0.00390625F;
        if (u % 1 != 0)
            f = 1;
        if (v % 1 != 0)
            f1 = 1;
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV((double) (x), (double) (y + height),
                (double) this.zLevel, (double) ((float) (u) * f),
                (double) ((float) (v + height) * f1));
        tessellator.addVertexWithUV((double) (x + width),
                (double) (y + height), (double) this.zLevel,
                (double) ((float) (u + width) * f),
                (double) ((float) (v + height) * f1));
        tessellator.addVertexWithUV((double) (x + width), (double) (y),
                (double) this.zLevel, (double) ((float) (u + width) * f),
                (double) ((float) (v) * f1));
        tessellator.addVertexWithUV((double) (x), (double) (y),
                (double) this.zLevel, (double) ((float) (u) * f),
                (double) ((float) (v) * f1));
        tessellator.draw();
    }

    public void drawRectangleStretched(int x, int y, float u, float v, int width,
                                       int height, float uOff, float vOff) {
        drawRectangleStretched(texture, x, y, u, v, width, height, uOff, vOff);
    }

    public void drawRectangleStretched(ResourceLocation texture, int x, int y, float u, float v, int width,
                                       int height, float uOff, float vOff) {
        drawRectangleStretched(texture, x, y, u, v, width,
                height, u + uOff, v + vOff, true);
    }

    public void drawRectangleStretched(ResourceLocation texture, int x, int y, float u, float v, int width,
                                       int height, float uMax, float vMax, boolean max) {
        if (max) {
            RenderHelper.bindTexture(texture);
            float f = 0.00390625F;
            float f1 = 0.00390625F;
            if (u % 1 != 0)
                f = 1;
            if (v % 1 != 0)
                f1 = 1;
            Tessellator tessellator = Tessellator.instance;
            tessellator.startDrawingQuads();
            tessellator.addVertexWithUV((double) (x), (double) (y + height),
                    (double) this.zLevel, (double) u * f,
                    (double) vMax * f1);
            tessellator.addVertexWithUV((double) (x + width),
                    (double) (y + height), (double) this.zLevel,
                    (double) uMax * f,
                    (double) vMax * f1);
            tessellator.addVertexWithUV((double) (x + width), (double) (y),
                    (double) this.zLevel, (double) uMax * f,
                    (double) v * f1);
            tessellator.addVertexWithUV((double) (x), (double) (y),
                    (double) this.zLevel, (double) (u) * f,
                    (double) (v) * f1);
            tessellator.draw();
        } else {
            drawRectangleStretched(texture, x, y, u, v, width, height, uMax, vMax);
        }
    }

    public void drawRectangleRepeated(ResourceLocation texture, int x, int y, float u, float v, float uvWidth, float uvHeight, int width, int height) {
        drawRectangleRepeated(texture, x, y, u, v, uvWidth, uvHeight, width, height, (int) uvWidth, (int) uvHeight);
    }

    public void drawRectangleRepeated(ResourceLocation texture, int x, int y, float u, float v, float uvWidth, float uvHeight, int width, int height, int tileWidth, int tileHeight) {
        int numX = (int) Math.ceil((float) width / tileWidth);
        int numY = (int) Math.ceil((float) height / tileHeight);

        int scale = RenderHelper.computeGuiScale();

        GL11.glEnable(GL11.GL_SCISSOR_TEST);
        GL11.glScissor((x) * scale, mc.displayHeight - (y + height) * scale, width * scale, height * scale);

        for (int y2 = 0; y2 < numY; ++y2)
            for (int x2 = 0; x2 < numX; ++x2) {
                int xOffset = x2 * tileWidth;
                int yOffset = y2 * tileHeight;

                this.drawRectangleStretched(texture, x + xOffset, y + yOffset,
                        u, v, tileWidth, tileHeight,
                        uvWidth, uvHeight);
            }
        GL11.glDisable(GL11.GL_SCISSOR_TEST);
    }

    public void drawRectangleXRepeated(ResourceLocation texture, int x, int y, float u, float v, float uvWidth, float uvHeight, int width, int height) {
        RenderHelper.bindTexture(texture);
        float f = 0.00390625F;
        float f1 = 0.00390625F;
        if (u % 1 != 0)
            f = 1;
        if (v % 1 != 0)
            f1 = 1;
        Tessellator tessellator = Tessellator.instance;

        boolean flipX = width < 0;
        if (flipX) width *= -1;

        int numX = (int) Math.ceil((float) width / uvWidth);

        int scale = RenderHelper.computeGuiScale();

        GL11.glEnable(GL11.GL_SCISSOR_TEST);
        GL11.glScissor((x) * scale, mc.displayHeight - (y + height) * scale, width * scale, height * scale);

        for (int x2 = 0; x2 < numX; ++x2) {
            float xOffset = x2 * uvWidth;
            if (flipX)
                xOffset = width - (x2 + 1) * uvWidth;

            tessellator.startDrawingQuads();
            tessellator.addVertexWithUV((double) (x + xOffset), (double) (y + height),
                    (double) this.zLevel, (double) ((float) (u) * f),
                    (double) (v + uvHeight) * f1);
            tessellator.addVertexWithUV((double) (x + uvWidth + xOffset),
                    (double) (y + height), (double) this.zLevel,
                    (double) ((float) (u + uvWidth) * f),
                    (double) ((float) (v + uvHeight) * f1));
            tessellator.addVertexWithUV((double) (x + uvWidth + xOffset), (double) (y),
                    (double) this.zLevel, (double) ((float) (u + uvWidth) * f),
                    (double) ((float) (v) * f1));
            tessellator.addVertexWithUV((double) (x + xOffset), (double) (y),
                    (double) this.zLevel, (double) ((float) (u) * f),
                    (double) ((float) (v) * f1));
            tessellator.draw();
        }
        GL11.glDisable(GL11.GL_SCISSOR_TEST);
    }

    public void drawRectangleYRepeated(ResourceLocation texture, int x, int y, float u, float v, float uvWidth, float uvHeight, int width, int height) {
        RenderHelper.bindTexture(texture);
        float f = 0.00390625F;
        float f1 = 0.00390625F;
        if (u % 1 != 0)
            f = 1;
        if (v % 1 != 0)
            f1 = 1;
        Tessellator tessellator = Tessellator.instance;

        boolean flipY = height < 0;
        if (flipY) height *= -1;

        int numY = (int) Math.ceil((float) height / uvHeight);

        int scale = RenderHelper.computeGuiScale();

        GL11.glEnable(GL11.GL_SCISSOR_TEST);
        GL11.glScissor((x) * scale, mc.displayHeight - (y + height) * scale, width * scale, height * scale);

        for (int y2 = 0; y2 < numY; ++y2) {
            float yOffset = y2 * uvHeight;
            if (flipY)
                yOffset = height - (y2 + 1) * uvHeight;

            tessellator.startDrawingQuads();
            tessellator.addVertexWithUV((double) (x), (double) (y + uvHeight + yOffset),
                    (double) this.zLevel, (double) ((float) (u) * f),
                    (double) ((float) (v + uvHeight) * f1));
            tessellator.addVertexWithUV((double) (x + width),
                    (double) (y + uvHeight + yOffset), (double) this.zLevel,
                    (double) ((float) (u + uvWidth) * f),
                    (double) ((float) (v + uvHeight) * f1));
            tessellator.addVertexWithUV((double) (x + width), (double) (y + yOffset),
                    (double) this.zLevel, (double) ((float) (u + uvWidth) * f),
                    (double) ((float) (v) * f1));
            tessellator.addVertexWithUV((double) (x), (double) (y + yOffset),
                    (double) this.zLevel, (double) ((float) (u) * f),
                    (double) ((float) (v) * f1));
            tessellator.draw();
        }
        GL11.glDisable(GL11.GL_SCISSOR_TEST);
    }

    public abstract void draw(int mouseX, int mouseY);

    public abstract void update(int mouseX, int mouseY);

    public void setZIndex(int zIndex) {
        this.zLevel = zIndex;
    }

    public void setScreenPos(int screenX, int screenY) {
        this.screenX = screenX;
        this.screenY = screenY;
    }

    public int getScreenX() {
        return this.screenX;
    }

    public int getScreenY() {
        return this.screenY;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return visible;
    }

    public void notifyListeners(Class<? extends Listener> clazz,
                                String methodName, Object... params) {
        for (Listener listener : listeners) {
            if (clazz.isInstance(listener)) {

                Class<?>[] paramTypes = new Class<?>[params.length];
                for (int i = 0; i < params.length; i++) {
                    if (Primitives.isWrapperType(params[i].getClass()))
                        paramTypes[i] = Primitives.unwrap(params[i].getClass());
                    else
                        paramTypes[i] = params[i].getClass();
                }
                try {
                    Method method = null;
                    for (Method meth : clazz.getMethods()) {
                        if (meth.getName().equals(methodName)) {
                            if (Arrays.deepEquals(paramTypes,
                                    meth.getParameterTypes()))
                                method = meth;
                        }
                    }

                    if (method != null)
                        method.invoke(listener, params);
                } catch (IllegalArgumentException e) {
                    throw new RuntimeException(
                            "Unexpected Reflection exception during event construction!",
                            e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(
                            "Unexpected Reflection exception during event construction!",
                            e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(
                            "Unexpected Reflection exception during event construction!",
                            e);
                }
            }
        }
    }

    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    public boolean isHovered(int mouseX, int mouseY) {
        return false;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setTexture(ResourceLocation texture) {
        this.texture = texture;
    }

    public boolean isInsideRegion(int x, int y, int minX, int minY, int maxX, int maxY) {
        return x > minX && y > minY && x < maxX && y < maxY;
    }

    public void drawGradientRect(int par1, int par2, int par3, int par4, int par5, int par6) {
        float f = (float) (par5 >> 24 & 255) / 255.0F;
        float f1 = (float) (par5 >> 16 & 255) / 255.0F;
        float f2 = (float) (par5 >> 8 & 255) / 255.0F;
        float f3 = (float) (par5 & 255) / 255.0F;
        float f4 = (float) (par6 >> 24 & 255) / 255.0F;
        float f5 = (float) (par6 >> 16 & 255) / 255.0F;
        float f6 = (float) (par6 >> 8 & 255) / 255.0F;
        float f7 = (float) (par6 & 255) / 255.0F;
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glShadeModel(GL11.GL_SMOOTH);
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.setColorRGBA_F(f1, f2, f3, f);
        tessellator.addVertex((double) par3, (double) par2, (double) this.zLevel);
        tessellator.addVertex((double) par1, (double) par2, (double) this.zLevel);
        tessellator.setColorRGBA_F(f5, f6, f7, f4);
        tessellator.addVertex((double) par1, (double) par4, (double) this.zLevel);
        tessellator.addVertex((double) par3, (double) par4, (double) this.zLevel);
        tessellator.draw();
        GL11.glShadeModel(GL11.GL_FLAT);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
