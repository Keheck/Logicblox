package mod.keheck.logicblox.elements.blocks.memory.latches;

import mod.keheck.logicblox.bases.GateBase;
import mod.keheck.logicblox.init.BlockInit;
import mod.keheck.logicblox.init.ItemInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

/**
 * Is used for memory storage and is only writable
 * when the write input is powered
 */

public class BlockGatedLatch extends GateBase
{
    public BlockGatedLatch(String name) { super(name); }

    @Override
    public boolean canConnectRedstone(IBlockState state, IBlockAccess world, BlockPos pos, @Nullable EnumFacing side)
    {
        return side != state.getValue(FACING).rotateY();
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        //super.neighborChanged(state, worldIn, pos, blockIn, fromPos);

        IBlockState writeState = worldIn.getBlockState(pos.offset(state.getValue(FACING).rotateY()));
        IBlockState inputState = worldIn.getBlockState(pos.offset(state.getValue(FACING)));

        boolean writeConnected = writeState.getBlock() == Blocks.REDSTONE_WIRE;
        boolean inputConnected = inputState.getBlock() == Blocks.REDSTONE_WIRE;

        //Checks if the inputs are connected
        if(writeConnected && inputConnected)
        {
            boolean inputPowered = inputState.getValue(BlockRedstoneWire.POWER) > 0;
            boolean writePowered = writeState.getValue(BlockRedstoneWire.POWER) > 0;

            if(writePowered && inputPowered)
            {
                worldIn.setBlockState(pos, state.withProperty(ACTIVE, true));
            }
            else if(writePowered)
            {
                worldIn.setBlockState(pos, state.withProperty(ACTIVE, false));
            }
        }
        //the inputConnected is not required,
        //since it only has an effect when
        //the write enable is connected
        else if(writeConnected)
        {
            worldIn.setBlockState(pos, state.withProperty(ACTIVE, false));
        }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) { return ItemInit.ITEM_LATCH_GATED; }

    @Override
    protected IBlockState getPoweredState(IBlockState unpoweredState)
    {
        return BlockInit.LATCH_GATED.getDefaultState().withProperty(FACING, unpoweredState.getValue(FACING));
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        super.onBlockAdded(worldIn, pos, state);
    }

    @Override
    protected IBlockState getUnpoweredState(IBlockState poweredState)
    {
        return BlockInit.LATCH_GATED.getDefaultState().withProperty(FACING, poweredState.getValue(FACING));
    }
}
