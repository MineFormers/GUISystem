package de.mineformers.gui.api.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * GUISystem
 * <p/>
 * StackFilter
 *
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class StackFilter
{

    private boolean filterType, filterDamage, filterNBT;
    private String type;
    private int damage;
    private NBTTagCompound nbt;

    public StackFilter(String item)
    {
        this(item, -1, null);
    }

    public StackFilter(String item, int damage)
    {
        this(item, damage, null);
    }

    public StackFilter(String item, int damage, NBTTagCompound nbt)
    {
        this.type = item;
        this.damage = damage;
        this.nbt = nbt;
        this.filterType = item != null;
        this.filterDamage = damage != -1;
        this.filterNBT = nbt != null;
    }

    public String getType()
    {
        return (filterType) ? type : null;
    }

    public int getDamage()
    {
        return (filterDamage) ? damage : -1;
    }

    public NBTTagCompound getNBT()
    {
        return (filterNBT) ? nbt : null;
    }

    @Override
    public int hashCode()
    {
        return 0;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj != null)
        {
            if (obj instanceof ItemStack)
            {
                ItemStack stack = (ItemStack) obj;
                return (stack.getItem().getUnlocalizedName(stack).equals(type) || this.filterType == false) &&
                        (stack.getItemDamage() == damage || this.filterDamage == false) &&
                        (stack.getTagCompound().equals(nbt) || this.filterNBT == false);
            }

            if (obj instanceof StackFilter)
            {
                StackFilter filter = (StackFilter) obj;
                return (filter.getType().equals(type) || this.filterType == false) &&
                        (filter.getDamage() == damage || this.filterDamage == false) &&
                        (filter.getNBT().equals(nbt) || this.filterNBT == false);
            }
        }

        return false;
    }
}
