package de.mineformers.example;

import de.mineformers.gui.component.UIComponent;
import de.mineformers.gui.component.canvas.UICanvas;
import de.mineformers.gui.component.container.UIWindow;
import de.mineformers.gui.component.decorative.UILabel;
import de.mineformers.gui.component.interaction.*;
import de.mineformers.gui.component.inventory.UITank;
import de.mineformers.gui.component.layout.UIFlowLayout;
import de.mineformers.gui.component.layout.UIRadioButtonGroup;
import de.mineformers.gui.component.layout.UITableLayout;
import de.mineformers.gui.listener.ListenerClickable;
import de.mineformers.gui.listener.ListenerMouseScroll;
import de.mineformers.gui.util.MouseButton;
import de.mineformers.gui.util.Orientation;
import de.mineformers.gui.util.Padding;
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
        UIWindow window = new UIWindow(200, 250);
        UITableLayout layout = new UITableLayout();

        layout.addComponent(new UILabel("§lDemo GUI"), 0, 0);
        layout.addComponent(new UILabel("TextBox:"), 1, 0);
        layout.addComponent(new UITextBox(100, 15, "Demo", true), 1, 1);
        layout.addComponent(new UILabel("Button:"), 2, 0);
        UIButton button = new UIButton(100, 20, "§kDemo");
        button.addListener(new ListenerClickable() {
            @Override
            public void onClick(UIComponent component, int mouseX, int mouseY, MouseButton mouseBtn) {
                mc.currentScreen = null;
                mc.setIngameFocus();
            }
        });

        button.addListener(new ListenerMouseScroll() {
            @Override
            public void onMouseScroll(UIComponent component, int dir, int mouseX, int mouseY) {

            }
        });

        layout.addComponent(button, 2, 1);

        layout.addComponent(new UILabel("Navigation:"), 3, 0);
        UIFlowLayout navLayout = new UIFlowLayout(30, 15);
        navLayout.setPadding(new Padding(2, 1));
        navLayout.addComponent(new UINavigationButton(UINavigationButton.TYPE_PREV));
        navLayout.addComponent(new UINavigationButton(UINavigationButton.TYPE_NEXT));
        layout.addComponent(navLayout, 3, 1);

        layout.addComponent(new UILabel("Progress Bar:"), 4, 0);
        progressBar = new UIProgressBarScalable(Orientation.VERTICAL_TOP, 8, 32, 86, 14, 4, 8);
        progressBar.setMaxValue(4000);
        final UITank tank = new UITank(100, 50, new FluidStack(FluidRegistry.LAVA, 8000));
        progressBar.setValue(progressBar.getMaxValue());
        progressBar.addListener(new ListenerMouseScroll() {
            @Override
            public void onMouseScroll(UIComponent component, int dir, int mouseX, int mouseY) {
                progressBar.updateValue(-dir * 10);

                progressBar.setValue(MathHelper.clamp_int(progressBar.getValue(), 0, progressBar.getMaxValue()));
                tank.setFluidAmount(progressBar.getValue() * 2);
            }
        });
        layout.addComponent(progressBar, 4, 1);
        layout.addComponent(new UILabel("Tank:"), 5, 0);
        tank.setDrawSlot(true);
        layout.addComponent(tank, 5, 1);
        layout.addComponent(new UILabel("Checkbox:"), 6, 0);
        layout.addComponent(new UICheckBox("Demo"), 6, 1);
        layout.addComponent(new UILabel("Radio Buttons:"), 7, 0);
        UIRadioButtonGroup stack = new UIRadioButtonGroup();
        stack.setPadding(new Padding(2, 2));
        stack.addComponent(new UIRadioButton("test1", "Test 1"));
        stack.addComponent(new UIRadioButton("test2", "Test 2"));
        layout.addComponent(stack, 7, 1);
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

