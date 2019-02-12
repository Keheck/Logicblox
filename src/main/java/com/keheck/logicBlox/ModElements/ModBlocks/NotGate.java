package com.keheck.logicBlox.ModElements.ModBlocks;

import com.keheck.logicBlox.bases.GateBase;
import com.keheck.logicBlox.init.BlockInit;
import com.keheck.logicBlox.init.ItemInit;
import com.keheck.logicBlox.util.Interfaces.Markers.OneIn;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class NotGate extends GateBase implements OneIn
{
    public NotGate(String name) { super(name); }

    @Override
    protected IBlockState getPoweredState(IBlockState unpoweredState)
    {
        EnumFacing facing = unpoweredState.getValue(FACING);
        return BlockInit.NOT_GATE.getDefaultState().withProperty(FACING, facing).withProperty(ACTIVE, true);
    }

    @Override
    protected IBlockState getUnpoweredState(IBlockState poweredState)
    {
        EnumFacing facing = poweredState.getValue(FACING);
        return BlockInit.NOT_GATE.getDefaultState().withProperty(FACING, facing).withProperty(ACTIVE, false);
    }

    @Override
    protected boolean shouldBePowered(World worldIn, BlockPos pos, IBlockState state)
    {
        return this.calculateInputStrength(worldIn, pos, state) == 0;
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
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
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) { return ItemInit.NOT_GATE_BLOCK_ITEM; }
}
