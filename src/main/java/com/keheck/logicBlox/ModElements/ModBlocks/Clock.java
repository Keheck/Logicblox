package com.keheck.logicBlox.ModElements.ModBlocks;

import com.keheck.logicBlox.bases.GateBase;
import com.keheck.logicBlox.init.BlockInit;
import com.keheck.logicBlox.init.ItemInit;
import com.keheck.logicBlox.util.Interfaces.Markers.OneIn;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class Clock extends GateBase implements OneIn
{
    public Clock(String name) { super(name); }

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

    @Override
    public boolean isPowered(IBlockState state) { return state.getValue(ACTIVE); }

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
        return BlockInit.CLOCK.getDefaultState().withProperty(FACING, facing).withProperty(ACTIVE, true);
    }

    @Override
    protected IBlockState getUnpoweredState(IBlockState poweredState)
    {
        EnumFacing facing = poweredState.getValue(FACING);
        return BlockInit.CLOCK.getDefaultState().withProperty(FACING, facing).withProperty(ACTIVE, false);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return ItemInit.CLOCK_ITEM;
    }
}
