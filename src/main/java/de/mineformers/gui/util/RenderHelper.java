package de.mineformers.gui.util;

import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.client.FMLClientHandler;

/**
 * 
 * GUISystem
 * 
 * RenderHelper
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
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
