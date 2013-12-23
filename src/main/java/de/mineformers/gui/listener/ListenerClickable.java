package de.mineformers.gui.listener;

import de.mineformers.gui.util.MouseButton;

/**
 * GUISystem
 * <p/>
 * ListenerClickable
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public interface ListenerClickable extends Listener {

    public void onClick(int mouseX, int mouseY, MouseButton mouseBtn);

}
