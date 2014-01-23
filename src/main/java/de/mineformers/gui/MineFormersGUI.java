package de.mineformers.gui;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import de.mineformers.gui.api.loader.UILoader;
import de.mineformers.gui.lib.Reference;

/**
 * GUISystem
 * <p/>
 * MineFormersGUI
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@Mod(modid = Reference.MOD_ID,
        name = Reference.MOD_NAME,
        dependencies = Reference.DEPENDENCIES,
        certificateFingerprint = Reference.FINGERPRINT)
public class MineFormersGUI
{

    @Mod.Instance(Reference.MOD_ID)
    public static MineFormersGUI instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        if (event.getSide().isClient())
        {
            UILoader.init();
        }
    }

}
