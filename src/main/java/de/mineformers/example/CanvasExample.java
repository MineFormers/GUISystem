package de.mineformers.example;

import de.mineformers.gui.component.canvas.UICanvas;
import de.mineformers.gui.component.container.UIWindow;
import de.mineformers.gui.component.decorative.UILabel;
import de.mineformers.gui.component.interaction.*;
import de.mineformers.gui.component.inventoy.UITank;
import de.mineformers.gui.component.layout.UIAbsoluteLayout;
import de.mineformers.gui.component.layout.UITableLayout;
import de.mineformers.gui.listener.ListenerClickable;
import de.mineformers.gui.listener.ListenerMouseScroll;
import de.mineformers.gui.util.MouseButton;
import de.mineformers.gui.util.Orientation;
import net.minecraft.util.MathHelper;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

/**
 * GUISystem
 * <p/>
 * CanvasExample
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CanvasExample extends UICanvas {

    private long last;
    private UIProgressBar progressBar;

    public CanvasExample() {
        super(0, 0);
        UIWindow window = new UIWindow(200, 200);
        UITableLayout layout = new UITableLayout();

        layout.addComponent(new UILabel("§lDemo GUI"), 0, 0);
        layout.addComponent(new UILabel("TextBox:"), 1, 0);
        layout.addComponent(new UITextBox(100, 15, "Demo", true), 1, 1);
        layout.addComponent(new UILabel("Button:"), 2, 0);
        UIButton button = new UIButton(100, 20, "§kDemo");
        button.addListener(new ListenerClickable() {
            @Override
            public void onClick(int mouseX, int mouseY, MouseButton mouseBtn) {
                mc.currentScreen = null;
                mc.setIngameFocus();
            }
        });

        button.addListener(new ListenerMouseScroll() {
            @Override
            public void onMouseScroll(int dir, int mouseX, int mouseY) {

            }
        });

        layout.addComponent(button, 2, 1);

        layout.addComponent(new UILabel("Navigation:"), 3, 0);
        UIAbsoluteLayout navLayout = new UIAbsoluteLayout();
        navLayout.addComponent(new UINavigationButton(UINavigationButton.TYPE_PREV), 0, 0);
        navLayout.addComponent(new UINavigationButton(UINavigationButton.TYPE_NEXT), 12, 0);
        layout.addComponent(navLayout, 3, 1);

        layout.addComponent(new UILabel("Progress Bar:"), 4, 0);
        progressBar = new UIProgressBarScalable(Orientation.VERTICAL_TOP, 8, 32, 86, 14, 4, 8);
        progressBar.setMaxValue(4000);
        final UITank tank = new UITank(100, 50, new FluidStack(FluidRegistry.LAVA, 8000));
        progressBar.setValue(progressBar.getMaxValue());
        progressBar.addListener(new ListenerMouseScroll() {
            @Override
            public void onMouseScroll(int dir, int mouseX, int mouseY) {
                progressBar.updateValue(-dir * 10);

                progressBar.setValue(MathHelper.clamp_int(progressBar.getValue(), 0, progressBar.getMaxValue()));
                tank.setFluidAmount(progressBar.getValue() * 2);
            }
        });
        layout.addComponent(progressBar, 4, 1);
        layout.addComponent(new UILabel("Tank:"), 5, 0);
        tank.setDrawSlot(true);
        layout.addComponent(tank, 5, 1);

        window.setLayout(layout);
        this.setPanel(window);
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        /*long current = System.currentTimeMillis();
        if (current - last >= 2000) {
            int oldValue = progressBar.getValue();
            progressBar.updateValue(-1);
            if (oldValue <= 0)
                progressBar.setValue(progressBar.getMaxValue());
        }*/
        super.draw(mouseX, mouseY);
    }
}

