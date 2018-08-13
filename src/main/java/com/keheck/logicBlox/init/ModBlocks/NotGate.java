package com.keheck.logicBlox.init.ModBlocks;

import com.keheck.logicBlox.bases.GateBase;
import com.keheck.logicBlox.init.BlockInit;
import com.keheck.logicBlox.init.ItemInit;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class NotGate extends GateBase
{
    public NotGate(String name, boolean isOpened)
    {
        super(name, isOpened);
    }

    @Override
    public int getWeakPower(IBlockState blockState, IBlockAccess access, BlockPos pos, EnumFacing facing)
    {
        if(this.isPowered(blockState))
        {
            return 0;
        }
        else
        {
            return blockState.getValue(FACING) == facing ? this.getActiveSignal(access, pos, blockState) : 0;
        }
    }

    @Override
    protected boolean isPowered(IBlockState state)
    {
        return this.isRepeaterPowered;
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
        return BlockInit.NOT_GATE_OPENED.getDefaultState().withProperty(FACING, facing);
    }

    @Override
    protected IBlockState getUnpoweredState(IBlockState poweredState)
    {
        EnumFacing facing = poweredState.getValue(FACING);
        return BlockInit.NOT_GATE_UNOPENED.getDefaultState().withProperty(FACING, facing);
    }

    @Override
    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        return state.withRotation(mirrorIn.toRotation(state.getValue(FACING)));
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (!this.isLocked(worldIn, pos, state))
        {
            boolean flag = this.shouldBePowered(worldIn, pos, state);

            if (this.isRepeaterPowered && !flag)
            {
                worldIn.setBlockState(pos, this.getUnpoweredState(state), 2);
            }
            else if (!this.isRepeaterPowered)
            {
                worldIn.setBlockState(pos, this.getPoweredState(state), 2);

                if (!flag)
                {
                    worldIn.updateBlockTick(pos, this.getPoweredState(state).getBlock(), this.getTickDelay(state), -1);
                }
            }
        }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return ItemInit.not_gate_block_item;
    }

    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(ItemInit.not_gate_block_item);
    }
}
