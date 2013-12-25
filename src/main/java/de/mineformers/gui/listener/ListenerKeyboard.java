package de.mineformers.gui.listener;

import de.mineformers.gui.component.UIComponent;

/**
 * GUISystem
 * <p/>
 * ListenerKeyboard
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public interface ListenerKeyboard extends Listener {

    public void onKeyTyped(UIComponent component, char keyChar, int keyCode);

}
