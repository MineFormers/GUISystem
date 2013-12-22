package de.mineformers.gui.util;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.util.ResourceLocation;

/**
 * GUISystem
 * <p/>
 * RenderHelper
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class RenderHelper {

    public static void bindTexture(ResourceLocation path) {
        FMLClientHandler.instance().getClient().getTextureManager()
                .bindTexture(path);
    }

    public static void bindTexture(String path) {
        bindTexture(ResourceHelper.getModResource(path));
    }

}
