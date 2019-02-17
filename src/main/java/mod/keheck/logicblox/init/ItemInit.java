package mod.keheck.logicblox.init;

import mod.keheck.logicblox.elements.items.ItemBlockSpecialMod;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemInit
{
    public static final List<Item> ITEMS = new ArrayList<>();

    /* new Items start */

    public static final Item REDSTONE_BRIDGE_ITEM        = new ItemBlockSpecialMod(BlockInit.REDSTONE_BRIDGE, "redstone_bridge_item");

    /* new Items end   */

    /*Gate Items*/
    public static final Item NOT_GATE_BLOCK_ITEM         = new ItemBlockSpecialMod(BlockInit.NOT_GATE, "not_gate_item");
    public static final Item AND_GATE_BLOCK_ITEM         = new ItemBlockSpecialMod(BlockInit.AND_GATE, "and_gate_item");
    public static final Item OR_GATE_BLOCK_ITEM          = new ItemBlockSpecialMod(BlockInit.OR_GATE, "or_gate_item");
    public static final Item XOR_GATE_BLOCK_ITEM         = new ItemBlockSpecialMod(BlockInit.XOR_GATE, "xor_gate_item");
    public static final Item NAND_GATE_BLOCK_ITEM        = new ItemBlockSpecialMod(BlockInit.NAND_GATE, "nand_gate_item");
    public static final Item NOR_GATE_BLOCK_ITEM         = new ItemBlockSpecialMod(BlockInit.NOR_GATE, "nor_gate_item");
    public static final Item XNOR_GATE_BLOCK_ITEM        = new ItemBlockSpecialMod(BlockInit.XNOR_GATE, "xnor_gate_item");
    public static final Item RSNOR_LATCH_ITEM            = new ItemBlockSpecialMod(BlockInit.NOR_LATCH, "nor_latch_item");
    public static final Item TFLIP_FLOP_ITEM             = new ItemBlockSpecialMod(BlockInit.TFLIP_FLOP, "tflip_flop_item");
    public static final Item CLOCK_ITEM                  = new ItemBlockSpecialMod(BlockInit.CLOCK, "clock_item");
    public static final Item RANDOMIZER_DIGITAL_ITEM     = new ItemBlockSpecialMod(BlockInit.RANDOMIZER_DIGITAL, "randomizer_digital_item");
    public static final Item RANDOMIZER_ANALOG_ITEM      = new ItemBlockSpecialMod(BlockInit.RANDOMIZER_ANALOG, "randomizer_analog_item");
    public static final Item RESISTOR_ITEM               = new ItemBlockSpecialMod(BlockInit.RESISTOR, "resistor_item");
}
