package de.mineformers.gui.component.interaction;

import de.mineformers.gui.component.UIComponent;
import de.mineformers.gui.listener.ListenerClickable;
import de.mineformers.gui.listener.ListenerMouseScroll;
import de.mineformers.gui.system.Global;
import de.mineformers.gui.util.MouseButton;
import de.mineformers.gui.util.Utilities;
import net.minecraft.util.MathHelper;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class UIScrollBar extends UIComponent {

    public int scrollY;
    public float scrollVisualY;

    private int barHeight;

    private int scrollStep;

    private int initialClickY;

    public UIScrollBar(int x, int y, int w, int h) {
        super(Global.getTexture());

        screenX = x;
        screenY = y;

        this.width = w;
        this.height = h;

        this.setBarHeight(this.height / 4);
        scrollStep = this.height / 10;

        this.addListener(new ListenerMouseScroll() {
            @Override
            public void onMouseScroll(int dir, int mouseX, int mouseY) {
                mouseScroll(dir, mouseX, mouseY);
            }
        });

        this.addListener(new ListenerClickable() {

            @Override
            public void onClick(int mouseX, int mouseY, MouseButton mouseBtn) {
                mouseClick(mouseX, mouseY, mouseBtn);
            }
        });
    }

    public UIScrollBar(int w, int h) {
        this(0, 0, w, h);
    }

    public void mouseClick(int mouseX, int mouseY, MouseButton mouseBtn) {
        initialClickY = mouseY - (screenY + scrollY);
    }

    public void mouseScroll(int dir, int mouseX, int mouseY) {
        scroll(dir * scrollStep);
    }

    @Override
    public boolean isHovered(int mouseX, int mouseY) {
        return true;
    }

    public void setScrollStep(int step) {
        this.scrollStep = step;
    }

    public int getScrollStep() {
        return this.scrollStep;
    }

    public void setBarHeight(int height) {
        this.barHeight = height;
    }

    public int getBarHeight() {
        return this.barHeight;
    }

    public void scroll(int amt) {
        this.scrollY += amt;
        clamp();
    }

    public void scrollTo(int y) {
        this.scrollY = y;
        clamp();
    }

    public void clamp() {
        this.scrollY = MathHelper.clamp_int(this.scrollY, 0, this.height - this.barHeight);
    }

    public float getScrollFraction() {
        return Math.round((scrollVisualY / (float) (height - barHeight)) * 1000f) / 1000f;
    }

    public int getScrollScaled(int scale) {
        return Math.round(getScrollFraction() * scale);
    }

    @Override
    public void update(int mouseX, int mouseY) {
        if (Mouse.isButtonDown(0)) {
            if (initialClickY >= 0) {
                this.scrollTo((mouseY - screenY) - initialClickY);
            }
        } else {
            initialClickY = -1;
        }
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        GL11.glDisable(GL11.GL_TEXTURE_2D);

        GL11.glColor4f(0.2f, 0.2f, 0.2f, 1);
        this.drawRectangle(screenX, screenY, 0, 0, width, height);

        this.scrollVisualY = Utilities.lerp(this.scrollVisualY, scrollY, 0.1f);
        // Slider
        GL11.glColor4f(0.5f, 0.5f, 0.5f, 1);
        this.drawRectangle(screenX, screenY + (int) scrollVisualY, 0, 0, width, barHeight + 1);
        GL11.glColor4f(0.6f, 0.6f, 0.6f, 1);
        this.drawRectangle(screenX, screenY + (int) scrollVisualY, 0, 0, width - 1, barHeight);

        GL11.glColor4f(0.5f, 0.5f, 0.5f, 1);
        int sh = barHeight / 4;
        for (int i = 0; i < sh; ++i) {
            int offset = 2 + (int) ((i / (float) sh) * (barHeight - 2));

            this.drawRectangle(screenX + 1, screenY + offset + (int) scrollVisualY, 0, 0, width - 3, 1);
        }

        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }

}
