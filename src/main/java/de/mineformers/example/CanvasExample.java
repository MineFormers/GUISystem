package de.mineformers.example;

import de.mineformers.gui.component.canvas.UICanvas;
import de.mineformers.gui.component.container.UIWindow;
import de.mineformers.gui.component.decorative.UIImage;
import de.mineformers.gui.component.interaction.UIButton;
import de.mineformers.gui.component.interaction.UINavigationButton;
import de.mineformers.gui.component.inventoy.UISlot;
import de.mineformers.gui.component.layout.UITableLayout;
import de.mineformers.gui.listener.ListenerClickable;
import de.mineformers.gui.util.LangHelper;
import de.mineformers.gui.util.ResourceHelper;

/**
 * GUISystem
 * <p/>
 * CanvasExample
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CanvasExample extends UICanvas {

    public CanvasExample() {
        super(0, 0);
        UIWindow window = new UIWindow(256, 210);

        UITableLayout layout = new UITableLayout();

        layout.addComponent(new UISlot(10, 10), 0, 0);

        final UINavigationButton btnPrev = new UINavigationButton(
                UINavigationButton.TYPE_PREV);
        final UINavigationButton btnNext = new UINavigationButton(
                UINavigationButton.TYPE_NEXT);
        UIButton btnTravel = new UIButton(95, 20,
                LangHelper.translate("gui", "button.travel"));
        UIButton btnCancel = new UIButton(95, 20,
                LangHelper.translate("gui", "button.cancel"));

        btnPrev.setEnabled(false);
        btnNext.setEnabled(true);
        btnTravel.setEnabled(true);

        btnPrev.addListener(new ListenerClickable() {

            @Override
            public void onClick(int mouseX, int mouseY) {

            }
        });

        btnNext.addListener(new ListenerClickable() {

            @Override
            public void onClick(int mouseX, int mouseY) {

            }
        });

        btnTravel.addListener(new ListenerClickable() {

            @Override
            public void onClick(int mouseX, int mouseY) {
                mc.currentScreen = null;
                mc.setIngameFocus();
            }
        });

        btnCancel.addListener(new ListenerClickable() {

            @Override
            public void onClick(int mouseX, int mouseY) {
                mc.currentScreen = null;
                mc.setIngameFocus();
            }
        });

        layout.addComponent(btnPrev, 0, 1);
        layout.addComponent(btnNext, 1, 0);

        layout.addComponent(btnTravel, 1, 1);
        layout.addComponent(btnCancel, 1, 2);
        layout.addComponent(new UIImage(ResourceHelper.getModResource("textures/items/apple.png"), 2), 0, 2);

        window.setLayout(layout);
        this.setPanel(window);
    }

    @Override
    public void drawForeground(int mouseX, int mouseY) {
        super.drawForeground(mouseX, mouseY);

        this.mc.fontRenderer.drawString("Time Machine", x + 10, y + 10,
                0x404040, false);
    }

}

