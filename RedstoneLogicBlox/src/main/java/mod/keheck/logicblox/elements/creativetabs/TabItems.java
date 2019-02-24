package mod.keheck.logicblox.elements.creativetabs;

import mod.keheck.logicblox.init.ItemInit;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class TabItems extends CreativeTabs
{
    public TabItems()
    {
        super("litems");
    }

    @Override
    public ItemStack getTabIconItem()
    {
        return new ItemStack(ItemInit.INGRED_BASE_WIRE_NONE);
    }
}
