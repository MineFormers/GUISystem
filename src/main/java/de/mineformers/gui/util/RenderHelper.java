package de.mineformers.gui.util;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.Minecraft;
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

    public static int computeGuiScale() {
        Minecraft mc = Minecraft.getMinecraft();
        int scaleFactor = 1;

        int k = mc.gameSettings.guiScale;

        if (k == 0) {
            k = 1000;
        }

        while (scaleFactor < k && mc.displayWidth / (scaleFactor + 1) >= 320 && mc.displayHeight / (scaleFactor + 1) >= 240) {
            ++scaleFactor;
        }
        return scaleFactor;
    }

}
