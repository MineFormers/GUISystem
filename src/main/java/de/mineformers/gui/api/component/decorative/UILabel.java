package de.mineformers.gui.api.component.decorative;

import de.mineformers.gui.api.component.UIComponent;
import de.mineformers.gui.api.util.PropertyHelper;
import de.mineformers.gui.api.util.RenderHelper;
import org.lwjgl.util.Color;

/**
 * GUISystem
 * <p/>
 * UILabel
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class UILabel extends UIComponent
{

    private String text;
    private int color;
    private boolean drawShadow;

    public static UILabel create(String text)
    {
        return new UILabel().init("text", text);
    }

    @Override
    public void init(PropertyHelper properties)
    {
        this.color = properties.get("color", 0x404040);
        this.text = properties.get("text", "");
        this.width = this.getStringWidth(text);
        this.height = this.mc.fontRenderer.FONT_HEIGHT;
    }

    public void setDrawShadow(boolean drawShadow)
    {
        this.drawShadow = drawShadow;
    }

    public void setColor(Color color)
    {
        this.color = RenderHelper.getColorFromRGB(color);
    }

    public void setColor(int color)
    {
        this.color = color;
    }

    public Color getColorRGB()
    {
        return RenderHelper.getRGBFromColor(color);
    }

    public int getColor()
    {
        return color;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public String getText()
    {
        return text;
    }

    @Override
    public boolean isHovered(int mouseX, int mouseY)
    {
        return this.isInsideRegion(mouseX, mouseY, screenX, screenY, screenX + width, screenY + height);
    }

    @Override
    public void update(int mouseX, int mouseY)
    {

    }

    @Override
    public void draw(int mouseX, int mouseY)
    {
        this.drawString(text, screenX, screenY, color, drawShadow);
    }
}
