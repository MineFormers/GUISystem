package de.mineformers.gui.api.component.interaction;

import de.mineformers.gui.api.component.UIComponent;
import de.mineformers.gui.api.util.Orientation;
import de.mineformers.gui.api.util.PropertyHelper;

/**
 * GUISystem
 * <p/>
 * UIProgressBar
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class UIProgressBar extends UIComponent
{

    private int maxValue;
    private int value;
    private int u, v;
    private Orientation orientation;

    @Override
    public void init(PropertyHelper properties)
    {
        this.orientation = properties.get("orientation", Orientation.HORIZONTAL_LEFT);
        this.maxValue = properties.get("maximum", 100);
        this.value = 0;
        this.u = properties.get("u", 0);
        this.v = properties.get("v", 0);
    }

    public void setMaxValue(int maxValue)
    {
        this.maxValue = maxValue;
    }

    public int getMaxValue()
    {
        return maxValue;
    }

    public void setValue(int value)
    {
        this.value = value;
    }

    public void updateValue(int amount)
    {
        value += amount;
        if (value >= maxValue)
            value = maxValue;
    }

    public int getValue()
    {
        return value;
    }

    public int getValueScaled(int scale)
    {
        return this.value * scale / maxValue;
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
        this.drawRectangle(screenX, screenY, u, v, width, height);
        switch (orientation)
        {
            case HORIZONTAL_LEFT:
                this.drawRectangle(screenX, screenY, u, v + height, getValueScaled(width), height);
                break;
            case HORIZONTAL_RIGHT:
                this.drawRectangle(screenX + (width - getValueScaled(width)), screenY, u + (width - getValueScaled(width)), v + height, getValueScaled(width), height);
                break;
            case VERTICAL_TOP:
                this.drawRectangle(screenX, screenY, u + width, v, width, getValueScaled(height));
                break;
            case VERTICAL_BOTTOM:
                this.drawRectangle(screenX, screenY + (height - getValueScaled(height)), u + width, v + (height - getValueScaled(height)), width, getValueScaled(height));
                break;
        }
    }
}
