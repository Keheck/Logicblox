package mod.keheck.logicblox.elements.blocks.diverse;

import mod.keheck.logicblox.bases.GateBase;
import mod.keheck.logicblox.init.BlockInit;
import mod.keheck.logicblox.init.ItemInit;
import mod.keheck.logicblox.util.interfaces.markers.NoActiveState;
import mod.keheck.logicblox.util.interfaces.markers.OneIn;
import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import java.util.Random;

public class BlockNoLatencyRepeater extends GateBase implements OneIn, NoActiveState
{
    public BlockNoLatencyRepeater(String name) { super(name); }

    @Override
    protected int getActiveSignal(IBlockAccess worldIn, BlockPos pos, IBlockState state)
    {
        IBlockState blockState = worldIn.getBlockState(pos.offset(state.getValue(FACING)));

        return blockState.getBlock() == Blocks.REDSTONE_WIRE && blockState.getValue(BlockRedstoneWire.POWER) > 0 ? 15 : 0;
    }

    @Override
    public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        return blockState.getValue(FACING) == side ? this.getActiveSignal(blockAccess, pos, blockState) : 0;
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, FACING);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;

        i |= state.getValue(FACING).getHorizontalIndex();

        return i;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return ItemInit.ITEM_DIVERSE_REPAETER;
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta));
    }

    @Override
    protected IBlockState getPoweredState(IBlockState unpoweredState)
    {
        return BlockInit.DIVERSE_REPEATER.getDefaultState().withProperty(FACING, unpoweredState.getValue(FACING));
    }

    @Override
    protected IBlockState getUnpoweredState(IBlockState poweredState)
    {
        return BlockInit.DIVERSE_REPEATER.getDefaultState().withProperty(FACING, poweredState.getValue(FACING));
    }
}
