package mod.keheck.logicblox.elements.blocks.gates;

import mod.keheck.logicblox.bases.GateBase;
import mod.keheck.logicblox.init.BlockInit;
import mod.keheck.logicblox.init.ItemInit;
import mod.keheck.logicblox.util.interfaces.markers.TwoIn;
import net.minecraft.block.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

/**
 * is active unless no inputs are active
 */

public class BlockOr extends GateBase implements TwoIn
{
    public BlockOr(String name) { super(name); }

    @Override
    protected IBlockState getPoweredState(IBlockState unpoweredState)
    {
        EnumFacing facing = unpoweredState.getValue(FACING);
        return BlockInit.GATE_OR.getDefaultState().withProperty(FACING, facing).withProperty(ACTIVE, true);
    }

    @Override
    protected IBlockState getUnpoweredState(IBlockState poweredState)
    {
        EnumFacing facing = poweredState.getValue(FACING);
        return BlockInit.GATE_OR.getDefaultState().withProperty(FACING, facing).withProperty(ACTIVE, false);
    }

    /**
     * calculates if the gate is open or not
     */
    @Override
    protected boolean shouldBePowered(World worldIn, BlockPos pos, IBlockState state)
    {
        EnumFacing facing1 = state.getValue(FACING).rotateY();
        EnumFacing facing2 = state.getValue(FACING).rotateYCCW();

        BlockPos pos1 = pos.offset(facing1);
        BlockPos pos2 = pos.offset(facing2);

        IBlockState state1 = worldIn.getBlockState(pos1);
        IBlockState state2 = worldIn.getBlockState(pos2);

        Block block1 = state1.getBlock();
        Block block2 = state2.getBlock();

        boolean inputstrength1;
        boolean inputstrength2;

        if(block1 == Blocks.LEVER)
        {
            inputstrength1 = state1.getValue(BlockLever.POWERED);
        }
        else if(block1 == Blocks.REDSTONE_WIRE)
        {
            inputstrength1 = state1.getValue(BlockRedstoneWire.POWER) > 0;
        }
        else
        {
            inputstrength1 = false;
        }

        if(block2 == Blocks.LEVER)
        {
            inputstrength2 = state2.getValue(BlockLever.POWERED);
        }
        else if(block2 == Blocks.REDSTONE_WIRE)
        {
            inputstrength2 = state2.getValue(BlockRedstoneWire.POWER) > 0;
        }
        else
        {
            inputstrength2 = false;
        }

        return inputstrength1 || inputstrength2;
    }

    /*/**
     * handles the inputs and sets a new state and notifies the
     * neighbours of the block if nescessary
     *
    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {

    }*/

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        boolean flag = this.shouldBePowered(worldIn, pos, state);

        if(flag && !state.getValue(ACTIVE))
        {
            worldIn.setBlockState(pos, getPoweredState(state));
            worldIn.notifyNeighborsOfStateChange(pos, this, false);
        }
        else if(!flag && state.getValue(ACTIVE))
        {
            worldIn.setBlockState(pos, getUnpoweredState(state));
            worldIn.notifyNeighborsOfStateChange(pos, this, false);
        }

        worldIn.updateBlockTick(pos, state.getBlock(), this.getDelay(state), -1);
    }

    /**
     * @see GateBase#getItemDropped(IBlockState, Random, int)
     */
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) { return ItemInit.ITEM_GATE_OR; }
}
