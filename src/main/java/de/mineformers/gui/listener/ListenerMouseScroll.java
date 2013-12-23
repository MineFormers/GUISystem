package de.mineformers.gui.listener;

import de.mineformers.gui.component.UIComponent;

public interface ListenerMouseScroll extends Listener
{
	public void onMouseScroll(int dir, int mouseX, int mouseY);
}
