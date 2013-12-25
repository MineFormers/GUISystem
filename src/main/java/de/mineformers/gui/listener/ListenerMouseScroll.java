package de.mineformers.gui.listener;

import de.mineformers.gui.component.UIComponent;

public interface ListenerMouseScroll extends Listener {

    public void onMouseScroll(UIComponent component, int dir, int mouseX, int mouseY);

}
