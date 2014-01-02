package de.mineformers.gui.util.render;

import de.mineformers.gui.util.RenderHelper;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.IIcon;

/**
 * GUISystem
 * <p/>
 * IconDrawingHelper
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class IconDrawingHelper implements IDrawingHelper {

    private IIcon icon;

    public IconDrawingHelper(IIcon icon) {
        this.icon = icon;
    }

    @Override
    public void draw(int x, int y) {
        RenderHelper.drawRectangleStretched(TextureMap.locationItemsTexture, x, y, icon.getMinU(), icon.getMinV(), icon.getIconWidth(), icon.getIconHeight(), icon.getMaxU(), icon.getMaxV(), true, 1);
    }
}
