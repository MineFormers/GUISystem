package de.mineformers.gui.api.component;

import com.google.common.eventbus.EventBus;
import cpw.mods.fml.client.FMLClientHandler;
import de.mineformers.gui.api.event.Event;
import de.mineformers.gui.api.loader.UIProperty;
import de.mineformers.gui.api.util.PropertyHelper;
import de.mineformers.gui.api.util.RenderHelper;
import de.mineformers.gui.lib.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.util.Color;

import java.util.HashMap;

/**
 * GUISystem
 * <p/>
 * GuiComponent
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class UIComponent
{

    @UIProperty(name = "name", type = UIProperty.Type.STRING)
    private String name;
    private UIComponent parent;
    protected int screenX, screenY;
    protected Minecraft mc;
    protected ResourceLocation texture;
    private int zLevel;
    protected boolean visible;
    @UIProperty(name = "width", type = UIProperty.Type.INT)
    protected int width;
    @UIProperty(name = "height", type = UIProperty.Type.INT)
    protected int height;
    private String tooltip;

    private EventBus eventBus;

    public UIComponent()
    {
        this.mc = FMLClientHandler.instance().getClient();
        this.zLevel = 0;
        eventBus = new EventBus();
        this.visible = true;
    }

    public <T extends UIComponent> T init(Object... properties)
    {
        HashMap<String, Object> map = new HashMap<String, Object>();
        String lastKey = null;
        for (int i = 0; i < properties.length; i++)
        {
            if (i % 2 == 0)
            {
                lastKey = (String) properties[i];
            } else
            {
                map.put(lastKey, properties[i]);
            }
        }
        PropertyHelper helper = new PropertyHelper(map);
        name = helper.get("name", null, String.class);
        width = helper.get("width", 0);
        height = helper.get("height", 0);
        init(helper);
        this.texture = texture == null ? Reference.getTexture() : texture;
        return (T) this;
    }

    public abstract void init(PropertyHelper properties);

    public void setParent(UIComponent parent)
    {
        this.parent = parent;
    }

    public UIComponent getParent()
    {
        return parent;
    }

    public void initComponent()
    {
    }

    public void setTooltip(String tooltip)
    {
        this.tooltip = tooltip;
    }

    public String getTooltip()
    {
        return tooltip;
    }

    public int getStringWidth(String text)
    {
        return RenderHelper.getStringWidth(text);
    }

    public void drawString(String text, int x, int y, int color,
                           boolean drawShadow)
    {
        RenderHelper.drawString(text, x, y, color, drawShadow, zLevel);
    }

    public void drawSplitString(String text, int x, int y, int color,
                                boolean drawShadow)
    {
        RenderHelper.drawSplitString(text, x, y, color, drawShadow);
    }

    public void drawSplitStringCentered(String text, int x, int y, int color,
                                        boolean drawShadow, int canvasWidth)
    {
        RenderHelper.drawSplitStringCentered(text, x, y, color, drawShadow, canvasWidth);
    }

    public void drawRectangle(Color color, int x, int y, int width, int height)
    {
        RenderHelper.drawRectangle(color, x, y, width, height, zLevel);
    }

    public void drawRectangle(int color, int x, int y, int width, int height)
    {
        RenderHelper.drawRectangle(color, x, y, width, height, zLevel);
    }

    public void drawRectangle(int color, float alpha, int x, int y, int width, int height)
    {
        RenderHelper.drawRectangle(color, alpha, x, y, width, height, zLevel);
    }

    public void drawRectangle(int x, int y, int u, int v, int width, int height)
    {
        drawRectangle(texture, x, y, u, v, width, height);
    }

    public void drawRectangle(ResourceLocation texture, int x, int y, float u,
                              float v, int width, int height)
    {
        RenderHelper.drawRectangle(texture, x, y, u, v, width, height, zLevel);
    }

    public void drawRectangleStretched(int x, int y, float u, float v, int width,
                                       int height, float uOff, float vOff)
    {
        drawRectangleStretched(texture, x, y, u, v, width, height, uOff, vOff);
    }

    public void drawRectangleStretched(ResourceLocation texture, int x, int y, float u, float v, int width,
                                       int height, float uOff, float vOff)
    {
        drawRectangleStretched(texture, x, y, u, v, width,
                height, u + uOff, v + vOff, true);
    }

    public void drawRectangleStretched(ResourceLocation texture, int x, int y, float u, float v, int width,
                                       int height, float uMax, float vMax, boolean max)
    {
        RenderHelper.drawRectangleStretched(texture, x, y, u, v, width, height, uMax, vMax, max, zLevel);
    }

    public void drawRectangleRepeated(ResourceLocation texture, int x, int y, float u, float v, float uvWidth, float uvHeight, int width, int height)
    {
        drawRectangleRepeated(texture, x, y, u, v, uvWidth, uvHeight, width, height, (int) uvWidth, (int) uvHeight);
    }

    public void drawRectangleRepeated(ResourceLocation texture, int x, int y, float u, float v, float uvWidth, float uvHeight, int width, int height, int tileWidth, int tileHeight)
    {
        RenderHelper.drawRectangleRepeated(texture, x, y, u, v, uvWidth, uvHeight, width, height, tileWidth, tileHeight, zLevel);
    }

    public void drawRectangleXRepeated(ResourceLocation texture, int x, int y, float u, float v, float uvWidth, float uvHeight, int width, int height)
    {
        RenderHelper.drawRectangleXRepeated(texture, x, y, u, v, uvWidth, uvHeight, width, height, zLevel);
    }

    public void drawRectangleYRepeated(ResourceLocation texture, int x, int y, float u, float v, float uvWidth, float uvHeight, int width, int height)
    {
        RenderHelper.drawRectangleYRepeated(texture, x, y, u, v, uvWidth, uvHeight, width, height, zLevel);
    }

    public abstract void draw(int mouseX, int mouseY);

    public void drawForeground(int mouseX, int mouseY)
    {
    }

    public void drawBackground(int mouseX, int mouseY)
    {
    }

    public abstract void update(int mouseX, int mouseY);

    public void setZIndex(int zIndex)
    {
        this.zLevel = zIndex;
    }

    public int getZIndex()
    {
        return zLevel;
    }

    public void setScreenPos(int screenX, int screenY)
    {
        this.screenX = screenX;
        this.screenY = screenY;
    }

    public int getScreenX()
    {
        return this.screenX;
    }

    public int getScreenY()
    {
        return this.screenY;
    }

    public void setVisible(boolean visible)
    {
        this.visible = visible;
    }

    public boolean isVisible()
    {
        return visible;
    }

    public void addListener(Object listener)
    {
        eventBus.register(listener);
    }

    public void removeListener(Object listener)
    {
        eventBus.unregister(listener);
    }

    public void postEvent(Event event)
    {
        eventBus.post(event);
    }

    public boolean isHovered(int mouseX, int mouseY)
    {
        return false;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public void setTexture(ResourceLocation texture)
    {
        this.texture = texture;
    }

    public boolean isInsideRegion(int x, int y, int minX, int minY, int maxX, int maxY)
    {
        return x > minX && y > minY && x < maxX && y < maxY;
    }

    public void setSize(int width, int height)
    {
        this.width = width;
        this.height = height;
    }

    public String getName()
    {
        return name;
    }
}
