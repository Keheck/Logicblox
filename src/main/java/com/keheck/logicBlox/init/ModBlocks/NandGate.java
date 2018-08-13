package com.keheck.logicBlox.init.ModBlocks;

import com.keheck.logicBlox.bases.GateBase;
import com.keheck.logicBlox.init.BlockInit;
import com.keheck.logicBlox.init.ItemInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class NandGate extends GateBase
{

    public NandGate(String name, boolean isOpened)
    {
        super(name, isOpened);
    }

    @Override
    public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        if(!this.isPowered(blockState))
        {
            return 0;
        }
        else
        {
            return blockState.getValue(FACING) == side ? this.getActiveSignal(blockAccess, pos, blockState) : 0;
        }
    }

    @Override
    public int getStrongPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        return getWeakPower(blockState, blockAccess, pos, side);
    }

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

        int strength1;
        int strength2;

        if(block1 == Blocks.REDSTONE_BLOCK)
        {
            strength1 = 15;
        }
        else
        {
            strength1 = block1 == Blocks.REDSTONE_WIRE ? state1.getValue(BlockRedstoneWire.POWER) : 0;
        }

        if(block2 == Blocks.REDSTONE_BLOCK)
        {
            strength2 = 15;
        }
        else
        {
            strength2 = block2 == Blocks.REDSTONE_WIRE ? state2.getValue(BlockRedstoneWire.POWER) : 0;
        }

        return !(strength1 > 0 && strength2 > 0);
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
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

    @Override
    protected IBlockState getPoweredState(IBlockState unpoweredState)
    {
        EnumFacing facing = unpoweredState.getValue(FACING);
        return BlockInit.NAND_GATE_OPENED.getDefaultState().withProperty(FACING, facing);
    }

    @Override
    protected IBlockState getUnpoweredState(IBlockState poweredState)
    {
        EnumFacing facing = poweredState.getValue(FACING);
        return BlockInit.NAND_GATE_UNOPENED.getDefaultState().withProperty(FACING, facing);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return ItemInit.nand_gate_block_item;
    }

    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(ItemInit.nand_gate_block_item);
    }
}
