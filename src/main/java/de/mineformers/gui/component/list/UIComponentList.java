package de.mineformers.gui.component.list;

import de.mineformers.gui.component.UIComponent;
import de.mineformers.gui.component.layout.UIAbsoluteLayout;
import de.mineformers.gui.util.MouseButton;

public class UIComponentList extends UIList<UIAbsoluteLayout> {
    public UIComponentList(int width, int height) {
        super(width, height);
    }

    @Override
    public void addItem(UIAbsoluteLayout item) {
        super.addItem(item);
    }

    @Override
    public void onClick(UIComponent component, int mouseX, int mouseY, MouseButton mouseButton) {
        super.onClick(component, mouseX, mouseY, mouseButton);

        for (int i = 0; i < items.size(); ++i) {
            UIAbsoluteLayout l = items.get(i);

            l.mouseClick(mouseX, mouseY, mouseButton.ordinal());
        }
    }

    @Override
    public void onKeyTyped(UIComponent component, char keyChar, int keyCode) {
        super.onKeyTyped(component, keyChar, keyCode);

        for (int i = 0; i < items.size(); ++i) {
            UIAbsoluteLayout l = items.get(i);

            l.keyTyped(keyChar, keyCode);
        }
    }

    @Override
    public int getItemHeight() {
        return 20;
    }

    @Override
    public void update(int mouseX, int mouseY) {
        super.update(mouseX, mouseY);
    }

    @Override
    public void drawBackground(int mouseX, int mouseY) {
        super.drawBackground(mouseX, mouseY);

        for (int i = 0; i < items.size(); ++i) {
            UIComponent item = items.get(i);

            item.drawBackground(mouseX, mouseY);
        }
    }

    @Override
    public void drawForeground(int mouseX, int mouseY) {
        super.drawForeground(mouseX, mouseY);

        for (int i = 0; i < items.size(); ++i) {
            UIComponent item = items.get(i);

            item.drawForeground(mouseX, mouseY);
        }
    }

    @Override
    protected void drawItem(UIAbsoluteLayout item, int x, int y, int width,
                            int height) {

        item.setScreenPos(x, y);
        item.draw(mouseX, mouseY);

    }
}
