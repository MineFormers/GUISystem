package de.mineformers.example;

import com.google.common.eventbus.Subscribe;
import de.mineformers.gui.component.container.UITabWindow;
import de.mineformers.gui.component.decorative.UILabel;
import de.mineformers.gui.component.interaction.UIButton;
import de.mineformers.gui.component.interaction.UIProgressBar;
import de.mineformers.gui.component.interaction.UITextBox;
import de.mineformers.gui.component.inventory.UIInfoTab;
import de.mineformers.gui.component.inventory.UITank;
import de.mineformers.gui.component.layout.UITableLayout;
import de.mineformers.gui.event.MouseClickEvent;
import de.mineformers.gui.event.MouseScrollEvent;
import de.mineformers.gui.util.Orientation;
import de.mineformers.gui.util.ResourceHelper;
import de.mineformers.gui.util.render.IconDrawingHelper;
import de.mineformers.gui.util.render.ResourceDrawingHelper;
import de.mineformers.gui.xml.XMLLoader;
import net.minecraft.init.Items;
import net.minecraft.util.MathHelper;

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
        XMLLoader.init();
        XMLLoader loader = new XMLLoader(ResourceHelper.getModResource("xml/test.xml"));
        loader.construct(loader.load());
        UITableLayout layout = new UITableLayout().init();
        UITableLayout layout1 = new UITableLayout().init();
        layout1.addComponent(UILabel.create("§lTab works"));

        layout.addComponent(UILabel.create("§lDemo GUI"), 0, 0);
        layout.addComponent(UILabel.create("TextBox:"), 1, 0);
        layout.addComponent(new UITextBox().init("width", 100, "height", 15, "text", "Demo"), 1, 1);
        layout.addComponent(UILabel.create("Button:"), 2, 0);
        UIButton button = new UIButton().init("width", 100, "height", 20, "text", "§kDemo");
        button.addListener(this);
        layout.addComponent(button, 2, 1);

        this.addTab("table", "Table", new ResourceDrawingHelper(ResourceHelper.getModResource("textures/example.png"), 16, 16, 0, 0, 0.125F, 0.125F), layout);
        this.addTab("test", "Test", new ResourceDrawingHelper(ResourceHelper.getModResource("textures/example.png"), 16, 16, 0.125F, 0, 0.25F, 0.125F), layout1);
        UIInfoTab infoTab = UIInfoTab.create(layout1, new IconDrawingHelper(Items.redstone.getIconFromDamage(0)), Orientation.HORIZONTAL_RIGHT, "Test");
        infoTab.setColor(0xA11B08);
        this.addInfoTab(infoTab);
        this.addInfoTab(UIInfoTab.create(layout1, new IconDrawingHelper(Items.diamond.getIconFromDamage(0)), Orientation.HORIZONTAL_RIGHT, "Test"));
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

