package mod.keheck.logicblox.elements.items;

import mod.keheck.logicblox.Main;
import mod.keheck.logicblox.init.ItemInit;
import mod.keheck.logicblox.init.TabInit;
import mod.keheck.logicblox.util.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockSpecial;

public class ItemBlockSpecialMod extends ItemBlockSpecial implements IHasModel
{
    public ItemBlockSpecialMod(Block block, String name)
    {
        super(block);
        setRegistryName(name);
        setUnlocalizedName(name);
        setMaxStackSize(64);
        setCreativeTab(TabInit.TAB_BLOCKS);

        ItemInit.ITEMS.add(this);
    }

    @Override
    public void registerModels()
    {
        Main.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
