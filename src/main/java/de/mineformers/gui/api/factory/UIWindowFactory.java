package de.mineformers.gui.api.factory;

import de.mineformers.gui.api.component.container.UIWindow;
import de.mineformers.gui.api.loader.UIEntry;
import de.mineformers.gui.api.loader.UILoader;

/**
 * GUISystem
 * <p/>
 * UIWindowFactory
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class UIWindowFactory extends ComponentFactory<UIWindow>
{

    public UIWindowFactory()
    {
        super(UIWindow.class);
    }

    @Override
    public UIWindow construct(UILoader loader, UIEntry entry)
    {
        UIWindow window = new UIWindow().init();
        for (UIEntry infoTab : entry.getChildrenByName("InfoTab"))
        {

        }
        return null;
    }
}
