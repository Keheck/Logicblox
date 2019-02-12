package com.keheck.logicBlox.ModElements.ModBlocks;

import com.keheck.logicBlox.bases.GateBase;
import com.keheck.logicBlox.init.ItemInit;
import com.keheck.logicBlox.util.Interfaces.Markers.NoActiveState;
import net.minecraft.block.BlockRedstoneRepeater;
import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import javax.annotation.Nullable;
import java.util.Random;

public class RedstoneBridge extends GateBase implements NoActiveState
{
    public RedstoneBridge(String name) { super(name); }

    @Override
    public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        return isInputSide(side, blockState) ? 0 : getPowerFromSide(blockAccess, side, pos);
    }

    @Override
    protected BlockStateContainer createBlockState() { return new BlockStateContainer(this, FACING); }

    @Override
    public boolean canConnectRedstone(IBlockState state, IBlockAccess world, BlockPos pos, @Nullable EnumFacing side)
    {
        return true;
    }

    private boolean isInputSide(EnumFacing side, IBlockState state)
    {
        return state.getValue(FACING).getOpposite() == side || state.getValue(FACING).rotateY() == side;
    }

    private int getPowerFromSide(IBlockAccess blockAccess, EnumFacing facing, BlockPos pos)
    {
        IBlockState blockState = blockAccess.getBlockState(pos.offset(facing));

        if(blockState.getBlock() == Blocks.REDSTONE_WIRE)
            return blockState.getValue(BlockRedstoneWire.POWER);
        else if(blockState.getBlock() == Blocks.POWERED_REPEATER)
            return blockState.getValue(BlockRedstoneRepeater.FACING) == facing ? 15 : 0;

        return 0;
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i |= state.getValue(FACING).getHorizontalIndex();
        return i;
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta));
    }

    @Override
    protected IBlockState getPoweredState(IBlockState unpoweredState)
    {
        return unpoweredState.withProperty(FACING, unpoweredState.getValue(FACING));
    }

    @Override
    protected IBlockState getUnpoweredState(IBlockState poweredState)
    {
        return poweredState.withProperty(FACING, poweredState.getValue(FACING));
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) { return ItemInit.REDSTONE_BRIDGE_ITEM; }
}