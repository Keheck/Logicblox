package com.keheck.logicBlox.bases;

import com.keheck.logicBlox.Main;
import com.keheck.logicBlox.init.ItemInit;
import com.keheck.logicBlox.util.Interfaces.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel
{
    public ItemBase(String name)
    {
        setCreativeTab(CreativeTabs.REDSTONE);
        setMaxStackSize(16);
        setUnlocalizedName(name);
        setRegistryName(name);

        ItemInit.ITEMS.add(this);
    }

    @Override
    public void registerModels()
    {
        Main.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
