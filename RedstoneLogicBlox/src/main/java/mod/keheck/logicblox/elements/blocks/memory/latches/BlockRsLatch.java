package mod.keheck.logicblox.elements.blocks.memory.latches;

import mod.keheck.logicblox.bases.GateBase;
import mod.keheck.logicblox.init.BlockInit;
import mod.keheck.logicblox.init.ItemInit;
import mod.keheck.logicblox.util.interfaces.markers.TwoIn;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Has two inputs that set and reset the output
 */

public class BlockRsLatch extends GateBase implements TwoIn
{
    public BlockRsLatch(String name) { super(name); }

    @Override
    public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        return blockState.getValue(FACING) == side && blockState.getValue(ACTIVE) ? this.getActiveSignal(blockAccess, pos, blockState) : 0;
    }

    @Override
    public int getStrongPower(IBlockState blockState, IBlockAccess access, BlockPos pos, EnumFacing facing)
    {
        return getWeakPower(blockState, access, pos, facing);
    }

    @Override
    protected IBlockState getPoweredState(IBlockState unpoweredState)
    {
        EnumFacing facing = unpoweredState.getValue(FACING);
        return BlockInit.LATCH_RS.getDefaultState().withProperty(FACING, facing).withProperty(ACTIVE, true);
    }

    @Override
    protected IBlockState getUnpoweredState(IBlockState poweredState)
    {
        EnumFacing facing = poweredState.getValue(FACING);
        return BlockInit.LATCH_RS.getDefaultState().withProperty(FACING, facing).withProperty(ACTIVE, false);
    }

    private void changeState(World worldIn, BlockPos pos, IBlockState state)
    {
        //Sets up everything needed to calculate the inputs
        IBlockState state1 = worldIn.getBlockState(pos.offset(state.getValue(FACING).rotateYCCW()));
        IBlockState state2 = worldIn.getBlockState(pos.offset(state.getValue(FACING).rotateY()));

        Block setBlock = state1.getBlock();
        Block resetBlock = state2.getBlock();

        boolean isPowered = state.getValue(ACTIVE);

        if(setBlock == Blocks.REDSTONE_WIRE && resetBlock == Blocks.REDSTONE_WIRE)
        {
            boolean power1 = state1.getValue(BlockRedstoneWire.POWER) > 0;
            boolean power2 = state2.getValue(BlockRedstoneWire.POWER) > 0;
            boolean notIllegalState = !(power1 && power2);

            //Checks if the inputs don't cause an illegal state
            if(notIllegalState)
            {
                if(power1 && !isPowered)
                {
                    worldIn.setBlockState(pos, getPoweredState(state));
                    worldIn.notifyNeighborsOfStateChange(pos, this, false);
                }
                else if(power2 && isPowered)
                {
                    worldIn.setBlockState(pos, getUnpoweredState(state));
                    worldIn.notifyNeighborsOfStateChange(pos, this, false);
                }
            }
        }
        //Handles the case where only
        //the set side is connected
        else if(setBlock == Blocks.REDSTONE_WIRE)
        {
            boolean power1 = state1.getValue(BlockRedstoneWire.POWER) > 0;
            boolean change = (!isPowered && power1);

            if(change)
            {
                worldIn.setBlockState(pos, getPoweredState(state));
                worldIn.notifyNeighborsOfStateChange(pos, this, false);
            }
        }
        //Handles the case where only
        //the reset side is connected
        else if(resetBlock == Blocks.REDSTONE_WIRE)
        {
            boolean power2 = state2.getValue(BlockRedstoneWire.POWER) > 0;
            boolean change = (isPowered && power2);

            if(change)
            {
                worldIn.setBlockState(pos, this.getUnpoweredState(state));
                worldIn.notifyNeighborsOfStateChange(pos, this, false);
            }
        }
    }

    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) { this.changeState(worldIn, pos, state); }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        this.changeState(worldIn, pos, state);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) { return ItemInit.ITEM_LATCH_RS; }
}
