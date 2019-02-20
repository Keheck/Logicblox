package mod.keheck.logicblox.init;

import mod.keheck.logicblox.elements.blocks.diverse.*;
import mod.keheck.logicblox.elements.blocks.gates.*;
import mod.keheck.logicblox.elements.blocks.memory.flipflops.BlockToggleableFlipFlop;
import mod.keheck.logicblox.elements.blocks.memory.latches.BlockGatedLatch;
import mod.keheck.logicblox.elements.blocks.memory.latches.BlockRsLatch;
import mod.keheck.logicblox.elements.blocks.randomizer.BlockRandomizerAnalog;
import mod.keheck.logicblox.elements.blocks.randomizer.BlockRandomizerDigital;
import net.minecraft.block.Block;

import java.util.ArrayList;
import java.util.List;

public class BlockInit
{
    public static final List<Block> BLOCKS = new ArrayList<>();

    /* New Blocks */
    public static final BlockNoLatencyRepeater DIVERSE_REPEATER = new BlockNoLatencyRepeater("diverse_nolatencyrepeater");
    public static final BlockBridgeLeft DIVERSE_BRIDGE_LEFT = new BlockBridgeLeft("diverse_bridge_left");
    public static final BlockGatedLatch LATCH_GATED = new BlockGatedLatch("latch_gated");

    /* Randomizer*/
    public static final BlockRandomizerDigital RANDOMIZER_DIGITAL = new BlockRandomizerDigital("randomizer_digital");
    public static final BlockRandomizerAnalog RANDOMIZER_ANALOG = new BlockRandomizerAnalog("randomizer_analog");

    /* Logic gates */
    public static final BlockNot GATE_NOT = new BlockNot("gate_not");
    public static final BlockAnd GATE_AND = new BlockAnd("gate_and");
    public static final BlockNand GATE_NAND = new BlockNand("gate_nand");
    public static final BlockOr GATE_OR = new BlockOr("gate_or");
    public static final BlockNor GATE_NOR = new BlockNor("gate_nor");
    public static final BlockXOr GATE_XOR = new BlockXOr("gate_xor");
    public static final BlockXNor GATE_XNOR = new BlockXNor("gate_xnor");

    /* latches and flipflops */
    public static final BlockRsLatch LATCH_RS = new BlockRsLatch("latch_rs");
    public static final BlockToggleableFlipFlop FLIPFLOP_TOGGLEABLE = new BlockToggleableFlipFlop("flipflop_toggleable");

    /* Diverse */
    public static final BlockClock DIVERSE_CLOCK = new BlockClock("diverse_clock");
    public static final BlockResistor DIVERSE_RESISTOR = new BlockResistor("diverse_resistor");
    public static final BlockBridgeRight DIVERSE_BRIDGE_RIGHT = new BlockBridgeRight("diverse_bridge_right");
}
