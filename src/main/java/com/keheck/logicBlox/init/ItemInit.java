package com.keheck.logicBlox.init;

import com.keheck.logicBlox.bases.ItemBase;
import com.keheck.logicBlox.init.ModItems.ItemBlockSpecialMod;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemInit
{
    public static final List<Item> ITEMS = new ArrayList<>();

    /*Gate Items*/
    public static final Item not_gate_block_item = new ItemBlockSpecialMod(BlockInit.NOT_GATE_UNOPENED, "not_gate_item");
    public static final Item and_gate_block_item = new ItemBlockSpecialMod(BlockInit.AND_GATE_UNOPENED, "and_gate_item");
    public static final Item nand_gate_block_item = new ItemBlockSpecialMod(BlockInit.NAND_GATE_UNOPENED, "nand_gate_item");
    public static final Item or_gate_block_item = new ItemBlockSpecialMod(BlockInit.OR_GATE_UNOPENED, "or_gate_item");
    public static final Item nor_gate_block_item = new ItemBlockSpecialMod(BlockInit.NOR_GATE_UNOPENED, "nor_gate_item");
    public static final Item xor_gate_block_item = new ItemBlockSpecialMod(BlockInit.XOR_GATE_UNOPENED, "xor_gate_item");
    public static final Item xnor_gate_block_item = new ItemBlockSpecialMod(BlockInit.XNOR_GATE_UNOPENED, "xnor_gate_item");
}
