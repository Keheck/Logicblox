package mod.keheck.logicblox.elements.blocks.diverse;

import mod.keheck.logicblox.bases.GateBase;
import mod.keheck.logicblox.init.BlockInit;
import mod.keheck.logicblox.init.ItemInit;
import mod.keheck.logicblox.util.interfaces.markers.OneIn;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Acts like a normal 1 tick clock
 */

public class BlockClock extends GateBase implements OneIn
{
    public BlockClock(String name) { super(name); }

    @Override
    public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        if(!this.isPowered(blockState))
        {
            return 0;
        }
        else
        {
            return blockState.getValue(FACING) == side && blockState.getValue(ACTIVE) ? getActiveSignal(blockAccess, pos, blockState) : 0;
        }
    }

    @Override
    public int getStrongPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        return getWeakPower(blockState, blockAccess, pos, side);
    }

    /**
     * handles the main part of the clock
     */
    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        boolean change = this.calculateInputStrength(world, pos, state) > 0;
        boolean isOpen = this.isPowered(state);

        if(change && isOpen)
        {
            world.setBlockState(pos, this.getUnpoweredState(state));
        }
        else if(change)
        {
            world.setBlockState(pos, this.getPoweredState(state));
        }
        else
        {
            world.setBlockState(pos, this.getUnpoweredState(state));
        }
    }

    @Override
    protected IBlockState getPoweredState(IBlockState unpoweredState)
    {
        EnumFacing facing = unpoweredState.getValue(FACING);
        return BlockInit.DIVERSE_CLOCK.getDefaultState().withProperty(FACING, facing).withProperty(ACTIVE, true);
    }

    @Override
    protected int getDelay(IBlockState state)
    {
        return 2;
    }

    @Override
    protected IBlockState getUnpoweredState(IBlockState poweredState)
    {
        EnumFacing facing = poweredState.getValue(FACING);
        return BlockInit.DIVERSE_CLOCK.getDefaultState().withProperty(FACING, facing).withProperty(ACTIVE, false);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) { return ItemInit.ITEM_DIVERSE_CLOCK; }
}
