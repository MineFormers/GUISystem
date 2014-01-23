package de.mineformers.gui.lib;

import de.mineformers.gui.api.util.ResourceHelper;
import net.minecraft.util.ResourceLocation;

/**
 * GUISystem
 * <p/>
 * Reference
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Reference
{
    public static final String MOD_ID = "MFGUI";
    public static final String MOD_NAME = "MineFormersGUI";
    public static final String CHANNEL_NAME = MOD_ID;
    public static final String FINGERPRINT = "";
    public static final String RESOURCE_PATH = "robots";
    public static final String RESOURCE_PREFIX = RESOURCE_PATH + ":";
    public static final String DEPENDENCIES = "required-after:Forge@[10.12.0.1013,)";

    public static String TEXTURE_PATH = "textures/guiWidgets.png";

    public static ResourceLocation getTexture()
    {
        return ResourceHelper.getModResource(TEXTURE_PATH);
    }
}
