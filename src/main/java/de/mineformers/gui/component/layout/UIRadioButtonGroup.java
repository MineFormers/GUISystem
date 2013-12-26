package de.mineformers.gui.component.layout;

import de.mineformers.gui.component.UIComponent;
import de.mineformers.gui.component.interaction.UIRadioButton;
import de.mineformers.gui.listener.ListenerClickable;
import de.mineformers.gui.util.MouseButton;
import de.mineformers.gui.util.Padding;
import org.lwjgl.opengl.GL11;

/**
 * GUISystem
 * <p/>
 * UIRadioButtonGroup
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class UIRadioButtonGroup extends UILayout<UIRadioButtonGroup.RadioButtonGroupConstraints> {

    int lastId;

    public class RadioButtonGroupConstraints extends UILayout.LayoutConstraints {

        public int id;

        public RadioButtonGroupConstraints(int id) {
            this.id = id;
        }

    }

    private Padding padding;

    public UIRadioButtonGroup() {
        super();
        padding = Padding.VERTICAL5;
    }

    public void setPadding(Padding padding) {
        this.padding = padding;
    }

    public Padding getPadding() {
        return padding;
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        for (int i = 0, offY = 0; i < components.size(); i++) {
            UIComponent component = components.get(i);
            int x = screenX;
            int y = offY;
            offY += component.getHeight() + padding.bottom;
            component.setScreenPos(x, screenY + y);
            GL11.glColor4f(1, 1, 1, 1);
            component.draw(mouseX, mouseY);
        }
    }

    @Override
    public void addComponent(UIComponent component) {
        if (component instanceof UIRadioButton) {
            components.add(component);
            ((UIRadioButton) component).setGroupId(lastId);
            component.addListener(new ListenerClickable() {
                @Override
                public void onClick(UIComponent component, int mouseX, int mouseY, MouseButton mouseBtn) {
                    UIRadioButtonGroup.this.changeChecked(((UIRadioButton) component).getGroupId());
                }
            });
            constraints.add(new RadioButtonGroupConstraints(lastId));
            lastId += 1;
        }
    }

    public String getChecked() {
        for (int i = 0; i < components.size(); i++) {
            UIRadioButton button = (UIRadioButton) components.get(i);
            if (button.isChecked())
                return button.getKey();
        }

        return null;
    }

    public void changeChecked(int id) {
        for (int i = 0; i < components.size(); i++) {
            UIRadioButton button = (UIRadioButton) components.get(i);
            RadioButtonGroupConstraints rbgc = constraints.get(i);

            if (rbgc.id != id)
                button.setChecked(false);
            else
                button.setChecked(true);
        }
    }

}