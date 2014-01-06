package de.mineformers.gui.component.decorative;

import de.mineformers.gui.component.UIComponent;
import de.mineformers.gui.util.PropertyHelper;
import de.mineformers.gui.util.ResourceHelper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * GUISystem
 * <p/>
 * UIImage
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class UIImage extends UIComponent {

    private int scale;

    @Override
    public void init(PropertyHelper properties) {
        scale = properties.get("scale", 1);
        try {
            BufferedImage temp = ImageIO.read(mc.getResourceManager().getResource(properties.get("image", ResourceHelper.getResourceLocation("minecraft", "null"))).getInputStream());
            this.width = temp.getWidth() * scale;
            this.height = temp.getHeight() * scale;
            this.setTexture(properties.get("image", ResourceHelper.getResourceLocation("minecraft", "null")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isHovered(int mouseX, int mouseY) {
        return this.isInsideRegion(mouseX, mouseY, screenX, screenY, screenX + width, screenY + height);
    }

    @Override
    public void update(int mouseX, int mouseY) {

    }

    @Override
    public void draw(int mouseX, int mouseY) {
        if (!texture.getResourcePath().toLowerCase().startsWith("textures/items") && !texture.getResourcePath().toLowerCase().startsWith("texturs/blocks"))
            this.drawRectangle(screenX, screenY, 0, 0, width, height);
        else
            this.drawRectangleStretched(screenX, screenY, 0, 0, 16 * scale, 16 * scale, 256, 256);
    }
}
