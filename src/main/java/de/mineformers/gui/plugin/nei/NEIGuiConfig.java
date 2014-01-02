package de.mineformers.gui.plugin.nei;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import de.mineformers.gui.system.Global;

/**
 * GUISystem
 * <p/>
 * NEIGuiConfig
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class NEIGuiConfig implements IConfigureNEI {

    @Override
    public void loadConfig() {
        API.registerNEIGuiHandler(new NEIGuiHandler());
    }

    @Override
    public String getName() {
        return Global.MOD_ID + " GUI";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }
}
