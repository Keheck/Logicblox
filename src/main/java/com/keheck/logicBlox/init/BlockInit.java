package com.keheck.logicBlox.init;

import com.keheck.logicBlox.bases.BlockBase;
import com.keheck.logicBlox.init.ModBlocks.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import scala.tools.nsc.transform.patmat.Logic;

import java.util.ArrayList;
import java.util.List;

public class BlockInit
{
    public static final List<Block> BLOCKS = new ArrayList<Block>();

    public static final NotGate NOT_GATE_OPENED = new NotGate("not_gate_opened", true);
    public static final NotGate NOT_GATE_UNOPENED = new NotGate("not_gate_unopened", false);

    public static final AndGate AND_GATE_OPENED = new AndGate("and_gate_opened", true);
    public static final AndGate AND_GATE_UNOPENED = new AndGate("and_gate_unopened", false);

    public static final NandGate NAND_GATE_OPENED = new NandGate("nand_gate_opened", true);
    public static final NandGate NAND_GATE_UNOPENED = new NandGate("nand_gate_unopened", false);

    public static final OrGate OR_GATE_OPENED = new OrGate("or_gate_opened", true);
    public static final OrGate OR_GATE_UNOPENED = new OrGate("or_gate_unopened", false);

    public static final NorGate NOR_GATE_OPENED = new NorGate("nor_gate_opened", true);
    public static final NorGate NOR_GATE_UNOPENED = new NorGate("nor_gate_unopened", false);

    public static final XOrGate XOR_GATE_OPENED = new XOrGate("xor_gate_opened", true);
    public static final XOrGate XOR_GATE_UNOPENED = new XOrGate("xor_gate_unopened", false);

    public static final XnorGate XNOR_GATE_OPENED = new XnorGate("xnor_gate_opened", true);
    public static final XnorGate XNOR_GATE_UNOPENED = new XnorGate("xnor_gate_unopened", false);

    public static final HorizontalBlock UNPROGRAMMED_GATE_ONE_IN = new HorizontalBlock("unprogrammed_gate_one_in", Material.IRON);
    public static final HorizontalBlock UNPROGRAMMED_GATE_TWO_IN = new HorizontalBlock("unprogrammed_gate_two_in", Material.IRON);
}
