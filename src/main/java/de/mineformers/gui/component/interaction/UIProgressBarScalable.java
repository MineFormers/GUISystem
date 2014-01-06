package de.mineformers.gui.component.interaction;

import de.mineformers.gui.util.Orientation;
import de.mineformers.gui.util.PropertyHelper;

/**
 * GUISystem
 * <p/>
 * UIProgressBar
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class UIProgressBarScalable extends UIProgressBar {

    private int maxValue;
    private int value;
    private int u, v;
    private int uvWidth, uvHeight;
    private Orientation orientation;

    @Override
    public void init(PropertyHelper properties) {
        super.init(properties);
        this.uvWidth = properties.get("uvWidth", width);
        this.uvHeight = properties.get("uvHeight", height);
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void updateValue(int amount) {
        value += amount;
        if (value >= maxValue)
            value = maxValue;
    }

    public int getValue() {
        return value;
    }

    public int getValueScaled(int scale) {
        return this.value * scale / maxValue;
    }

    @Override
    public boolean isHovered(int mouseX, int mouseY) {
        return this.isInsideRegion(mouseX, mouseY, screenX, screenY, screenX + width, screenY + height);
    }

    @Override
    public void update(int mouseX, int mouseY) {

    }

    @Override
    public void draw(int mouseX, int mouseY) {
        this.drawRectangleXRepeated(this.texture, screenX, screenY, u, v, uvWidth, uvHeight, width, height);

        switch (orientation) {
            case HORIZONTAL_LEFT:
                this.drawRectangleXRepeated(this.texture, screenX + 1, screenY, u, v + uvHeight, uvWidth, uvHeight, getValueScaled(width - 2), height);
                break;
            case HORIZONTAL_RIGHT:
                this.drawRectangleXRepeated(this.texture, screenX + (width - getValueScaled(width - 2)) - 1, screenY, u, v + uvHeight, uvWidth, uvHeight, -getValueScaled(width - 2), height);
                break;
            case VERTICAL_TOP:
                this.drawRectangleYRepeated(this.texture, screenX + 1, screenY + (height - getValueScaled(height)), u, v + uvHeight, uvWidth, uvHeight, width - 2, -getValueScaled(height));
                break;
            case VERTICAL_BOTTOM:
                this.drawRectangleYRepeated(this.texture, screenX + 1, screenY, u, v + uvHeight, uvWidth, uvHeight, width - 2, getValueScaled(height));
                break;
        }
    }
}
