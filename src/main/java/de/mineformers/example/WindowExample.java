package de.mineformers.example;

import com.google.common.eventbus.Subscribe;
import de.mineformers.gui.component.container.UITabWindow;
import de.mineformers.gui.component.decorative.UILabel;
import de.mineformers.gui.component.interaction.*;
import de.mineformers.gui.component.inventory.UIInfoTab;
import de.mineformers.gui.component.inventory.UITank;
import de.mineformers.gui.component.layout.UIFlowLayout;
import de.mineformers.gui.component.layout.UIRadioButtonGroup;
import de.mineformers.gui.component.layout.UITableLayout;
import de.mineformers.gui.event.MouseClickEvent;
import de.mineformers.gui.event.MouseScrollEvent;
import de.mineformers.gui.util.Orientation;
import de.mineformers.gui.util.Padding;
import de.mineformers.gui.util.ResourceHelper;
import de.mineformers.gui.util.render.IconDrawingHelper;
import de.mineformers.gui.util.render.ResourceDrawingHelper;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
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
public class WindowExample extends UITabWindow {

    private long last;
    private UIProgressBar progressBar;
    private UITank tank;

    public WindowExample() {
        super();
        UITableLayout layout = new UITableLayout();
        UITableLayout layout1 = new UITableLayout();
        layout1.addComponent(new UILabel("§lTab works"));

        layout.addComponent(new UILabel("§lDemo GUI"), 0, 0);
        layout.addComponent(new UILabel("TextBox:"), 1, 0);
        layout.addComponent(new UITextBox(100, 15, "Demo", true), 1, 1);
        layout.addComponent(new UILabel("Button:"), 2, 0);
        UIButton button = new UIButton(100, 20, "§kDemo");
        button.addListener(this);
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

        progressBar.setValue(progressBar.getMaxValue());
        progressBar.addListener(this);
        layout.addComponent(progressBar, 4, 1);

        layout.addComponent(new UILabel("Tank:"), 5, 0);
        //tank = new UITank(100, 50, new FluidStack(Fluid("energon"), 8000));
        //tank.setDrawSlot(true);
        //layout.addComponent(tank, 5, 1);

        layout.addComponent(new UILabel("Checkbox:"), 6, 0);
        layout.addComponent(new UICheckBox("Demo"), 6, 1);
        layout.addComponent(new UILabel("Radio Buttons:"), 7, 0);
        UIRadioButtonGroup stack = new UIRadioButtonGroup();
        stack.setPadding(new Padding(2, 2));
        stack.addComponent(new UIRadioButton("test1", "Test 1"));
        stack.addComponent(new UIRadioButton("test2", "Test 2"));
        layout.addComponent(stack, 7, 1);
        this.addTab("table", "Table", new ResourceDrawingHelper(ResourceHelper.getModResource("textures/example.png"), 16, 16, 0, 0, 0.125F, 0.125F), layout);
        this.addTab("test", "Test", new ResourceDrawingHelper(ResourceHelper.getModResource("textures/example.png"), 16, 16, 0.125F, 0, 0.25F, 0.125F), layout1);
        UIInfoTab infoTab = new UIInfoTab(layout1, new IconDrawingHelper(Items.redstone.getIconFromDamage(0)), Orientation.HORIZONTAL_RIGHT, "Test");
        infoTab.setColor(0xA11B08);
        this.addInfoTab(infoTab);
        this.addInfoTab(new UIInfoTab(layout1, new IconDrawingHelper(Items.diamond.getIconFromDamage(0)), Orientation.HORIZONTAL_RIGHT, "Test"));
        this.setTabOrientation(Orientation.VERTICAL_TOP);
    }

    @Subscribe
    public void onClick(MouseClickEvent event) {
        if (event.getComponent() instanceof UIButton) {
            mc.func_147108_a(null);
            mc.setIngameFocus();
        }
    }

    @Subscribe
    public void onMouseScroll(MouseScrollEvent event) {
        progressBar.updateValue(-event.dir * 10);

        progressBar.setValue(MathHelper.clamp_int(progressBar.getValue(), 0, progressBar.getMaxValue()));
        //tank.setFluidAmount(progressBar.getValue() * 2);
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

