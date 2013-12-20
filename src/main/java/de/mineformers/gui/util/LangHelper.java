package de.mineformers.gui.util;

import net.minecraft.util.StatCollector;
import de.mineformers.gui.system.Global;

/**
 * Kybology
 * 
 * LangHelper
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class LangHelper {

	public static String translate(String key) {
		return StatCollector.translateToLocal(key);
	}

	public static String translate(String type, String key) {
		return translate(Global.MOD_ID, type, key);
	}

	public static String translate(String modid, String type, String key) {
		return translate(getString(modid, type, key));
	}

	public static String getString(String modid, String type, String key) {
		return type + "." + modid + ":" + key;
	}

}
