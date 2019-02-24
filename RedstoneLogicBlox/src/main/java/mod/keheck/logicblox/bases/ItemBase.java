package mod.keheck.logicblox.bases;

import mod.keheck.logicblox.Main;
import mod.keheck.logicblox.init.ItemInit;
import mod.keheck.logicblox.init.TabInit;
import mod.keheck.logicblox.util.enums.ETabType;
import mod.keheck.logicblox.util.interfaces.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel
{
    public ItemBase(String name)
    {
        setCreativeTab(TabInit.TAB_ITEMS);
        setMaxStackSize(16);
        setUnlocalizedName(name);
        setRegistryName(name);

        ItemInit.ITEMS.add(this);
    }

    @Override
    public void registerModels() { Main.proxy.registerItemRenderer(this, 0, "inventory"); }
}
