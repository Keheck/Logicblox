package com.keheck.logicBlox.ModElements.ModItems;

import com.keheck.logicBlox.Main;
import com.keheck.logicBlox.init.ItemInit;
import com.keheck.logicBlox.util.Interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlockSpecial;

public class ItemBlockSpecialMod extends ItemBlockSpecial implements IHasModel
{
    public ItemBlockSpecialMod(Block block, String name)
    {
        super(block);
        setRegistryName(name);
        setUnlocalizedName(name);
        setMaxStackSize(64);
        setCreativeTab(CreativeTabs.REDSTONE);

        ItemInit.ITEMS.add(this);
    }

    @Override
    public void registerModels()
    {
        Main.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
