package de.mineformers.gui.component.interaction;

import de.mineformers.gui.component.UIComponent;
import de.mineformers.gui.system.Global;

/**
 * GUISystem
 * <p/>
 * UIRadioButton
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class UIRadioButton extends UIComponent {

    private String key;
    private int groupId;
    private boolean checked;
    private String label;

    public UIRadioButton(String key, String label) {
        super(Global.getTexture());
        this.label = label;
        this.key = key;
    }

    @Override
    public void update(int mouseX, int mouseY) {

    }

    @Override
    public void draw(int mouseX, int mouseY) {
        if (!isChecked()) {
            this.drawRectangle(screenX, screenY, 56, 50, 16, 16);
        } else {
            this.drawRectangle(screenX, screenY, 74, 50, 16, 16);
        }

        this.drawString(label, screenX + 18, screenY + (getHeight() - mc.fontRenderer.FONT_HEIGHT) / 2, 0x404040, false);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void toggle() {
        this.checked = !checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isChecked() {
        return checked;
    }

    @Override
    public boolean isHovered(int mouseX, int mouseY) {
        return this.isInsideRegion(mouseX, mouseY, screenX, screenY, screenX + 16, screenY + 16);
    }

    @Override
    public int getWidth() {
        return 18 + this.getStringWidth(label);
    }

    @Override
    public int getHeight() {
        return 16;
    }

}
