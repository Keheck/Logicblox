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
import net.minecraft.block.Block;

import java.util.Random;

public class BlockClock extends GateBase implements OneIn
{
    public BlockClock(String name) { super(name); }

    /**
     * @see GateBase#getWeakPower(IBlockState, IBlockAccess, BlockPos, EnumFacing)
     */
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

    /**
     * @see GateBase#getStrongPower(IBlockState, IBlockAccess, BlockPos, EnumFacing)
     */
    @Override
    public int getStrongPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        return getWeakPower(blockState, blockAccess, pos, side);
    }

    /**
     * returns true if the block is powered
     */
    @Override
    public boolean isPowered(IBlockState state) { return state.getValue(ACTIVE); }

    /**
     * @see Block#updateTick(World, BlockPos, IBlockState, Random)
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

    /**
     * @see GateBase#getPoweredState(IBlockState)
     */
    @Override
    protected IBlockState getPoweredState(IBlockState unpoweredState)
    {
        EnumFacing facing = unpoweredState.getValue(FACING);
        return BlockInit.DIVERSE_CLOCK.getDefaultState().withProperty(FACING, facing).withProperty(ACTIVE, true);
    }

    /**
     * @see GateBase#getUnpoweredState(IBlockState)
     */
    @Override
    protected IBlockState getUnpoweredState(IBlockState poweredState)
    {
        EnumFacing facing = poweredState.getValue(FACING);
        return BlockInit.DIVERSE_CLOCK.getDefaultState().withProperty(FACING, facing).withProperty(ACTIVE, false);
    }

    /**
     * @see GateBase#getItemDropped(IBlockState, Random, int)
     */
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return ItemInit.ITEM_DIVERSE_CLOCK;
    }
}
