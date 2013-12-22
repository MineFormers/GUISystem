package de.mineformers.gui.component.layout;

import de.mineformers.gui.component.UIComponent;
import de.mineformers.gui.system.Global;

import java.util.LinkedList;

/**
 * GUISystem
 * <p/>
 * UILayout
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class UILayout<T extends UILayout.LayoutConstraints> extends UIComponent {

    public class LayoutConstraints {

    }

    protected LinkedList<UIComponent> components;
    protected LinkedList<T> constraints;

    public UILayout() {
        super(Global.getTexture());
        this.components = new LinkedList<>();
        this.constraints = new LinkedList<>();
    }

    @Override
    public void draw(int mouseX, int mouseY) {

    }

    public void addComponent(UIComponent component) {
        this.components.add(component);
    }

    public LinkedList<UIComponent> getComponents() {
        return components;
    }

    @Override
    public int getWidth() {
        int width = 0;
        for (UIComponent component : components) {
            width += component.getWidth();
        }
        return width;
    }

    @Override
    public int getHeight() {
        int height = 0;
        for (UIComponent component : components) {
            height += component.getHeight();
        }
        return height;
    }
}
