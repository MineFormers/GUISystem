package de.mineformers.gui.util;

import de.mineformers.gui.system.Global;
import net.minecraft.util.ResourceLocation;

/**
 * GUISystem
 * <p/>
 * ResourceHelper
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ResourceHelper {

    public static ResourceLocation getResourceLocation(String modId, String path) {
        return new ResourceLocation(modId, path);
    }

    public static ResourceLocation getModResource(String path) {
        return getResourceLocation(Global.MOD_ID.toLowerCase(), path);
    }

}
