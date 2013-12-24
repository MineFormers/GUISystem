package de.mineformers.gui.component.list;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.renderer.Tessellator;

import org.lwjgl.opengl.GL11;

import de.mineformers.gui.component.container.UIPanelScrollable;
import de.mineformers.gui.component.layout.UIAbsoluteLayout;
import de.mineformers.gui.component.layout.UILayout;
import de.mineformers.gui.util.RenderHelper;

public class UIList<T> extends UIPanelScrollable
{
	protected List<T> items;
	
	protected int mouseX;
	protected int mouseY;
	
	public boolean drawBackground;
	
	public UIList(int width, int height)
	{
		super(width, height);
		
		this.width = width;
		this.height = height;
		
		this.drawBackground = true;
		
		items = new ArrayList<T>();
	}
	
	protected String getStringFromObject(Object obj)
	{
		return obj.toString();
	}
	
	public void addItem(T item)
	{
		items.add(item);
	}
	
	public void populate(List<T> list)
	{
		for (T item : list)
		{
			this.addItem(item);
		}
	}
	
	public int getItemHeight()
	{
		return 16;
	}
	
	public int getSpacing()
	{
		return 4;
	}
	
	public int getScrollY()
	{
		int scrollScale = ((items.size() - 1) * getItemHeight()) + ((items.size() - 1) * getSpacing());
		return (int) scrollBar.getScrollScaled(scrollScale);
	}
	
	@Override
	public void update(int mouseX, int mouseY) {
		super.update(mouseX, mouseY);
		
		this.mouseX = mouseX;
		this.mouseY = mouseY;
	}
	
	
	@Override
	public void drawBackground(int mouseX, int mouseY) {
		super.drawForeground(mouseX, mouseY);
		
		if (this.drawBackground) {
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			
			Tessellator tessellator = Tessellator.instance;
			
			// Background
			GL11.glColor4f(0.35f, 0.35f, 0.35f, 1);
			this.drawRectangle(screenX, screenY, 0, 0, width, height);
			
			// Background Lighting Left
			GL11.glColor4f(0.1f, 0.1f, 0.1f, 1);
			this.drawRectangle(screenX, screenY, 0, 0, 1, height - 1);
	
			// Background Lighting Top
			GL11.glColor4f(0.1f, 0.1f, 0.1f, 1);
			this.drawRectangle(screenX, screenY, 0, 0, width - 1, 1);
			
			// Background Lighting Right
			GL11.glColor4f(1f, 1f, 1f, 1);
			this.drawRectangle(screenX + width - 1, screenY + 1, 0, 0, 1, height - 1);
			
			// Background Lighting Bottom
			GL11.glColor4f(1f, 1f, 1f, 1);
			this.drawRectangle(screenX + 1, screenY + height - 1, 0, 0, width - 1, 1);
			
			GL11.glEnable(GL11.GL_TEXTURE_2D);
		}
		
		for (int i = 0; i < items.size(); ++i)
		{
			T item = items.get(i);
			
			int yOffset = 1 + (i * getItemHeight()) + (i * getSpacing()) - getScrollY();
			
			drawItemBackground(item, screenX, screenY + yOffset, this.getWidth(), getItemHeight());
		}
	}

	@Override
	public void drawForeground(int mouseX, int mouseY) {
		super.drawForeground(mouseX, mouseY);
		
		for (int i = 0; i < items.size(); ++i)
		{
			T item = items.get(i);

			int yOffset = 1 + (i * getItemHeight()) + (i * getSpacing()) - getScrollY();
			
			drawItemForeground(item, screenX, screenY + yOffset, this.getWidth(), getItemHeight());
		}
	}
	
	@Override
	public void draw(int mouseX, int mouseY)
	{
		GL11.glPushAttrib(GL11.GL_SCISSOR_BIT);
		GL11.glEnable(GL11.GL_SCISSOR_TEST);
		
		int scale = RenderHelper.computeGuiScale();
		GL11.glScissor((screenX + 1) * scale, mc.displayHeight - (screenY + height - 1) * scale, (width - 2) * scale, (height - 2) * scale);
		
		
		drawItems(mouseX, mouseY);
		
		
		GL11.glDisable(GL11.GL_SCISSOR_TEST);
		GL11.glPopAttrib();
		//this.scrollBar.draw(mouseX, mouseY);
		
		super.draw(mouseX, mouseY);
	}
	
	protected void drawItems(int mouseX, int mouseY)
	{
		//GL11.glDisable(GL11.GL_TEXTURE_2D);

		GL11.glColor4f(0.7f, 0.7f, 0.7f, 1);
		//this.drawRectangle(screenX, screenY, 0, 0, 2100, 3330);
		
		int spacing = getSpacing();
		int count = items.size();

		int scrollY = getScrollY();
		
		for (int i = 0; i < count; ++i)
		{
			T item = items.get(i);
			
			int yOffset = 1 + (i * getItemHeight()) + (i * spacing) - scrollY;
			
			drawItem(item, screenX, screenY + yOffset, this.getWidth(), getItemHeight());
			
			
		}
		
		//GL11.glEnable(GL11.GL_TEXTURE_2D);
	}
	
	protected void drawItemBackground(T item, int x, int y, int width, int height) {
		
	}
	
	protected void drawItemForeground(T item, int x, int y, int width, int height) {
		
	}
	
	protected void drawItem(T item, int x, int y, int width, int height)
	{
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		
		GL11.glColor4f(0.6f, 0.6f, 0.6f, 1);
		this.drawRectangle(x, y, 0, 0, width, height);

		GL11.glColor4f(0.4f, 0.4f, 0.4f, 1);
		this.drawRectangle(x, y, 0, 0, width, 1);

		GL11.glEnable(GL11.GL_TEXTURE_2D);
		
		String str = getStringFromObject(item);
		this.drawString(str, x, y, 0, false);
		
		
	}
}
