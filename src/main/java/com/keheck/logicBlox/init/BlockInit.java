package com.keheck.logicBlox.init;

import com.keheck.logicBlox.ModElements.ModBlocks.*;
import net.minecraft.block.Block;

import java.util.ArrayList;
import java.util.List;

public class BlockInit
{
    public static final List<Block> BLOCKS = new ArrayList<>();

    /* New Blocks Start */

    public static final RedstoneBridge REDSTONE_BRIDGE = new RedstoneBridge("redstone_bridge");

    /* New Blocks End */


    public static final RandomizerDigital RANDOMIZER_DIGITAL = new RandomizerDigital("randomizer_digital");
    public static final RandomizerAnalog RANDOMIZER_ANALOG = new RandomizerAnalog("randomizer_analog");

    public static final Resistor RESISTOR = new Resistor("resistor");

    public static final NotGate NOT_GATE = new NotGate("not_gate");
    public static final AndGate AND_GATE = new AndGate("and_gate");
    public static final NandGate NAND_GATE = new NandGate("nand_gate");
    public static final OrGate OR_GATE = new OrGate("or_gate");
    public static final NorGate NOR_GATE = new NorGate("nor_gate");
    public static final XOrGate XOR_GATE = new XOrGate("xor_gate");
    public static final XnorGate XNOR_GATE = new XnorGate("xnor_gate");
    public static final RSNorLatch NOR_LATCH = new RSNorLatch("nor_latch");
    public static final TFlipFlop TFLIP_FLOP = new TFlipFlop("tflip_flop");
    public static final Clock CLOCK = new Clock("clock");
}
