package de.mineformers.gui.component;

import com.google.common.primitives.Primitives;
import cpw.mods.fml.client.FMLClientHandler;
import de.mineformers.gui.listener.Listener;
import de.mineformers.gui.util.RenderHelper;
import de.mineformers.gui.util.TextHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;

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

    public void drawRectangle(int x, int y, float u, float v, int width, int height) {
        drawRectangle(texture, x, y, u, v, width, height);
    }

    public void drawRectangle(ResourceLocation texture, int x, int y, float u,
                              float v, int width, int height) {
        RenderHelper.bindTexture(texture);
        float f = 0.00390625F;
        float f1 = 0.00390625F;
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
            if (u < 1)
                f = 1;
            if (v < 1)
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

    public abstract void draw(int mouseX, int mouseY);

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
}
