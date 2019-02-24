package mod.keheck.logicblox.elements.creativetabs;

import mod.keheck.logicblox.init.ItemInit;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class TabBlocks extends CreativeTabs
{
    public TabBlocks() { super("lblocks"); }

    @Override
    public ItemStack getTabIconItem() { return new ItemStack(ItemInit.ITEM_GATE_AND); }
}
