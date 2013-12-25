package de.mineformers.gui.component.layout;

import de.mineformers.gui.component.UIComponent;

/**
 * GUISystem
 * <p/>
 * UIAbsoluteLayout
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class UIAbsoluteLayout extends UILayout<UIAbsoluteLayout.AbsoluteLayoutConstraints> {

    public class AbsoluteLayoutConstraints extends UILayout.LayoutConstraints {

        public int x, y;

        public AbsoluteLayoutConstraints(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

    @Override
    public void addComponent(UIComponent component) {
        addComponent(component, 0, 0);
    }

    public void addComponent(UIComponent component, int x, int y) {
        components.add(component);
        constraints.add(new AbsoluteLayoutConstraints(x, y));
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        super.draw(mouseX, mouseY);

        for (int i = 0; i < components.size(); i++) {
            UIComponent component = components.get(i);
            AbsoluteLayoutConstraints alc = constraints.get(i);

            component.setScreenPos(screenX + alc.x, screenY + alc.y);
            component.draw(mouseX, mouseY);
        }
    }

    @Override
    public int getWidth() {
        int width = 0;
        for (int i = 0; i < components.size(); i++) {
            UIComponent component = components.get(i);
            AbsoluteLayoutConstraints alc = constraints.get(i);

            if (component.getWidth() + alc.x > width)
                width = component.getWidth() + alc.x;
        }
        return width;
    }

    @Override
    public int getHeight() {
        int height = 0;
        for (int i = 0; i < components.size(); i++) {
            UIComponent component = components.get(i);
            AbsoluteLayoutConstraints alc = constraints.get(i);

            if (component.getHeight() + alc.y > height)
                height = component.getHeight() + alc.y;
        }
        return height;
    }
}
