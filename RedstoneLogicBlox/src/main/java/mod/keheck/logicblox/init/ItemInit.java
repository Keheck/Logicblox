package mod.keheck.logicblox.init;

import mod.keheck.logicblox.bases.ItemBase;
import mod.keheck.logicblox.elements.items.ItemBlockSpecialMod;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemInit
{
    public static final List<Item> ITEMS = new ArrayList<>();

    /* new Items start */

    public static final Item INGRED_BASE_TORCH_NONE = new ItemBase("ingred_base_torch_none");
    public static final Item INGRED_BASE_WIRE_NONE  = new ItemBase("ingred_base_wire_none" );
    public static final Item INGRED_BASE_WIRE_TORCH = new ItemBase("ingred_base_wire_torch");

    public static final Item ITEM_DIVERSE_REPAETER  = new ItemBlockSpecialMod(BlockInit.DIVERSE_REPEATER, "diverse_nolatencyrepeater");
    public static final Item ITEM_LATCH_GATED       = new ItemBlockSpecialMod(BlockInit.LATCH_GATED, "latch_gated");

    /* new Items end   */

    /*Item Blocks */
    public static final Item ITEM_GATE_NOT             = new ItemBlockSpecialMod(BlockInit.GATE_NOT, "gate_not");
    public static final Item ITEM_GATE_AND             = new ItemBlockSpecialMod(BlockInit.GATE_AND, "gate_and");
    public static final Item ITEM_GATE_OR              = new ItemBlockSpecialMod(BlockInit.GATE_OR, "gate_or");
    public static final Item ITEM_GATE_XOR             = new ItemBlockSpecialMod(BlockInit.GATE_XOR, "gate_xor");
    public static final Item ITEM_GATE_NAND            = new ItemBlockSpecialMod(BlockInit.GATE_NAND, "gate_nand");
    public static final Item ITEM_GATE_NOR             = new ItemBlockSpecialMod(BlockInit.GATE_NOR, "gate_nor");
    public static final Item ITEM_GATE_XNOR            = new ItemBlockSpecialMod(BlockInit.GATE_XNOR, "gate_xnor");
    public static final Item ITEM_LATCH_RS             = new ItemBlockSpecialMod(BlockInit.LATCH_RS, "latch_rs");
    public static final Item ITEM_FLIPFLOP_TOGGLEABLE  = new ItemBlockSpecialMod(BlockInit.FLIPFLOP_TOGGLEABLE, "flipflop_toggleable");
    public static final Item ITEM_DIVERSE_CLOCK        = new ItemBlockSpecialMod(BlockInit.DIVERSE_CLOCK, "diverse_clock");
    public static final Item ITEM_RANDOMIZER_DIGITAL   = new ItemBlockSpecialMod(BlockInit.RANDOMIZER_DIGITAL, "randomizer_digital");
    public static final Item ITEM_RANDOMIZER_ANALOG    = new ItemBlockSpecialMod(BlockInit.RANDOMIZER_ANALOG, "randomizer_analog");
    public static final Item ITEM_DIVERSE_RESISTOR     = new ItemBlockSpecialMod(BlockInit.DIVERSE_RESISTOR, "diverse_resistor");

    public static final Item ITEM_DIVERSE_BRIDGE_RIGHT = new ItemBlockSpecialMod(BlockInit.DIVERSE_BRIDGE_RIGHT, "diverse_bridge_right");
    public static final Item ITEM_DIVERSE_BRIDGE_LEFT  = new ItemBlockSpecialMod(BlockInit.DIVERSE_BRIDGE_LEFT,  "diverse_bridge_left");
}
