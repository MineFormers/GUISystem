package de.mineformers.gui.system;

import de.mineformers.gui.util.ResourceHelper;
import net.minecraft.util.ResourceLocation;

/**
 * 
 * GUISystem
 * 
 * Global
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class Global {

	public static String MOD_ID = "minecraft";

	public static String TEXTURE_PATH = "textures/gui/widgets.png";

	public static ResourceLocation getTexture() {
		return ResourceHelper.getModResource(TEXTURE_PATH);
	}

}
