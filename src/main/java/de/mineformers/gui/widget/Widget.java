package de.mineformers.gui.widget;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;

import com.google.common.primitives.Primitives;

import cpw.mods.fml.client.FMLClientHandler;
import de.mineformers.gui.util.TextHelper;
import de.mineformers.gui.util.RenderHelper;
import de.mineformers.gui.widget.listener.Listener;

/**
 * Kybology
 * 
 * Widget
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public abstract class Widget {

	protected int screenX, screenY;
	protected int x, y;
	protected Minecraft mc;
	protected ResourceLocation texture;
	private int zLevel;
	protected boolean visible;

	private ArrayList<Listener> listeners;

	public Widget(ResourceLocation texture, int x, int y) {
		this.mc = FMLClientHandler.instance().getClient();
		this.texture = texture;
		this.zLevel = 0;
		this.x = x;
		this.y = y;
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

	public void drawRectangle(int x, int y, int u, int v, int width, int height) {
		drawRectangle(texture, x, y, u, v, width, height);
	}

	public void drawRectangle(ResourceLocation texture, int x, int y, int u,
			int v, int width, int height) {
		RenderHelper.bindTexture(texture);
		float f = 0.00390625F;
		float f1 = 0.00390625F;
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV((double) (x + 0), (double) (y + height),
				(double) this.zLevel, (double) ((float) (u + 0) * f),
				(double) ((float) (v + height) * f1));
		tessellator.addVertexWithUV((double) (x + width),
				(double) (y + height), (double) this.zLevel,
				(double) ((float) (u + width) * f),
				(double) ((float) (v + height) * f1));
		tessellator.addVertexWithUV((double) (x + width), (double) (y + 0),
				(double) this.zLevel, (double) ((float) (u + width) * f),
				(double) ((float) (v + 0) * f1));
		tessellator.addVertexWithUV((double) (x + 0), (double) (y + 0),
				(double) this.zLevel, (double) ((float) (u + 0) * f),
				(double) ((float) (v + 0) * f1));
		tessellator.draw();
	}

	public void drawRectangleStretched(int x, int y, int u, int v, int width,
			int height, int uOff, int vOff) {
		float f = 0.00390625F;
		float f1 = 0.00390625F;
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV((double) (x + 0), (double) (y + height),
				(double) this.zLevel, (double) ((float) (u + 0) * f),
				(double) ((float) (v + vOff) * f1));
		tessellator.addVertexWithUV((double) (x + width),
				(double) (y + height), (double) this.zLevel,
				(double) ((float) (u + uOff) * f),
				(double) ((float) (v + vOff) * f1));
		tessellator.addVertexWithUV((double) (x + width), (double) (y + 0),
				(double) this.zLevel, (double) ((float) (u + uOff) * f),
				(double) ((float) (v + 0) * f1));
		tessellator.addVertexWithUV((double) (x + 0), (double) (y + 0),
				(double) this.zLevel, (double) ((float) (u + 0) * f),
				(double) ((float) (v + 0) * f1));
		tessellator.draw();
	}

	public abstract void draw(int mouseX, int mouseY);

	public void setZIndex(int zIndex) {
		this.zLevel = zIndex;
	}

	public void setPos(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setScreenPos(int screenX, int screenY) {
		this.screenX = screenX;
		this.screenY = screenY;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public boolean isVisible() {
		return visible;
	}

	protected void notifyListeners(Class<? extends Listener> clazz,
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

}
