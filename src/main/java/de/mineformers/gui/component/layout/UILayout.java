package de.mineformers.gui.component.layout;

import de.mineformers.gui.component.UIComponent;
import de.mineformers.gui.component.container.UIPanel;
import de.mineformers.gui.listener.ListenerClickable;
import de.mineformers.gui.listener.ListenerKeyboard;
import de.mineformers.gui.listener.ListenerMouseScroll;
import de.mineformers.gui.system.Global;
import de.mineformers.gui.util.MouseButton;
import org.lwjgl.input.Mouse;

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
    public void initComponent() {
    	super.initComponent();

        for (UIComponent component : components)
            component.initComponent();
    }	
    
    @Override
    public void update(int mouseX, int mouseY) {
        for (UIComponent component : components)
            component.update(mouseX, mouseY);
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

    public void mouseScroll(int dir, int mouseX, int mouseY) {
        for (UIComponent component : components) {
            if (component.isVisible())
                if (component.isHovered(mouseX, mouseY)) {
                    component.notifyListeners(ListenerMouseScroll.class,
                            "onMouseScroll", dir, mouseX, mouseY);
                } else if (component instanceof UIPanel) {
                    ((UIPanel) component).mouseScroll(dir, mouseX, mouseY);
                } else if (component instanceof UILayout) {
                    ((UILayout) component).mouseScroll(dir, mouseX, mouseY);
                }
        }
    }

    public void mouseClick(int mouseX, int mouseY, int mouseButton) {
        for (UIComponent component : components) {
            if (component.isVisible())
                if (component.isHovered(mouseX, mouseY)) {
                    component.notifyListeners(ListenerClickable.class,
                            "onClick", mouseX, mouseY, MouseButton.values()[mouseButton]);
                } else if (component instanceof UIPanel) {
                    ((UIPanel) component).mouseClick(mouseX, mouseY, mouseButton);
                } else if (component instanceof UILayout) {
                    ((UILayout) component).mouseClick(mouseX, mouseY, mouseButton);
                }
        }
    }

    public void keyTyped(char keyChar, int keyCode) {
        for (UIComponent component : components) {
            if (component.isVisible()) {
                if (component instanceof UIPanel)
                    ((UIPanel) component).keyTyped(keyChar, keyCode);
                else if (component instanceof UILayout)
                    ((UILayout) component).keyTyped(keyChar, keyCode);
                else
                    component.notifyListeners(ListenerKeyboard.class,
                            "onKeyTyped", keyChar, keyCode);
            }
        }
    }
}
