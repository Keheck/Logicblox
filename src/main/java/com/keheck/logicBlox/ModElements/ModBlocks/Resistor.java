package com.keheck.logicBlox.ModElements.ModBlocks;

import com.keheck.logicBlox.bases.GateBase;
import com.keheck.logicBlox.init.BlockInit;
import com.keheck.logicBlox.init.ItemInit;
import com.keheck.logicBlox.ModElements.TileEntitys.TileEntityResistor;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class Resistor extends GateBase implements ITileEntityProvider
{
    public Resistor(String name) { super(name); }

    @Override
    public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        return blockState.getValue(FACING) == side ? this.getActiveSignal(blockAccess, pos, blockState) : 0;
    }

    @Override
    public int getActiveSignal(IBlockAccess worldIn, BlockPos pos, IBlockState state)
    {
        TileEntity entity = worldIn.getTileEntity(pos);
        if(entity instanceof TileEntityResistor)
        {
            return ((TileEntityResistor)entity).getOutput();
        }
        return 0;
    }

    @Override
    public boolean canConnectRedstone(IBlockState state, IBlockAccess world, BlockPos pos, @Nullable EnumFacing side)
    {
        EnumFacing facing = state.getValue(FACING);
        return facing == side || facing.getOpposite() == side || facing.rotateYCCW() == side;
    }

    @Override
    protected IBlockState getPoweredState(IBlockState unpoweredState)
    {
        EnumFacing facing = unpoweredState.getValue(FACING);
        return BlockInit.RESISTOR.getDefaultState().withProperty(FACING, facing).withProperty(ACTIVE, true);
    }

    @Override
    protected IBlockState getUnpoweredState(IBlockState poweredState)
    {
        EnumFacing facing = poweredState.getValue(FACING);
        return BlockInit.RESISTOR.getDefaultState().withProperty(FACING, facing).withProperty(ACTIVE, true);
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        int input = this.calculateInputStrength(worldIn, pos, state);

        if (this.isRepeaterPowered)
        {
            worldIn.setBlockState(pos, this.getUnpoweredState(state), 4);
        }

        this.onStateChange(worldIn, pos, state, input);
    }

    private void onStateChange(World worldIn, BlockPos pos, IBlockState state, int input)
    {
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        BlockPos redStonePos = pos.offset(state.getValue(FACING).rotateY());
        IBlockState redStoneState = worldIn.getBlockState(redStonePos);
        Block redStone = redStoneState.getBlock();

        if(tileEntity instanceof TileEntityResistor)
        {
            TileEntityResistor entity = (TileEntityResistor)tileEntity;

            int res = 0;

            if(redStone == Blocks.REDSTONE_WIRE)
            {
                res = redStoneState.getValue(BlockRedstoneWire.POWER);
            }

            entity.setResistance(res);
            entity.setInput(input);

            this.notifyNeighbors(worldIn, pos, state);
        }
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        super.onBlockAdded(worldIn, pos, state);
        worldIn.setTileEntity(pos, this.createNewTileEntity(worldIn, 0));
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) { return new TileEntityResistor(); }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) { return ItemInit.RESISTOR_ITEM; }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        this.updateTick(worldIn, pos, state, null);
    }

    @Override
    public void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos neighbor)
    {
        if (pos.getY() == neighbor.getY() && world instanceof World && !((World) world).isRemote)
        {
            neighborChanged(world.getBlockState(pos), (World)world, pos, world.getBlockState(neighbor).getBlock(), neighbor);
        }
    }

    @Override
    public boolean getWeakChanges(IBlockAccess world, BlockPos pos) { return true; }
}
