package de.mineformers.gui.factory;

import de.mineformers.gui.component.container.UIWindow;
import de.mineformers.gui.xml.XMLEntry;
import de.mineformers.gui.xml.XMLLoader;

/**
 * GUISystem
 * <p/>
 * UIWindowFactory
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class UIWindowFactory extends ComponentFactory<UIWindow> {

    public UIWindowFactory() {
        super(UIWindow.class);
    }

    @Override
    public UIWindow construct(XMLLoader loader, XMLEntry entry) {
        UIWindow window = new UIWindow().init();
        for (XMLEntry infoTab : entry.getChildrenByName("InfoTab")) {

        }
        return null;
    }
}
