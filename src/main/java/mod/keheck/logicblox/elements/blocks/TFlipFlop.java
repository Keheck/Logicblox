package mod.keheck.logicblox.elements.blocks;

import mod.keheck.logicblox.bases.GateBase;
import mod.keheck.logicblox.init.BlockInit;
import mod.keheck.logicblox.init.ItemInit;
import mod.keheck.logicblox.elements.tileentities.TileEntityTFlipFlop;
import mod.keheck.logicblox.util.interfaces.markers.OneIn;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class TFlipFlop extends GateBase implements OneIn, ITileEntityProvider
{
    public TFlipFlop(String name) { super(name); }

    @Override
    public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        return blockState.getValue(FACING) == side ? this.getActiveSignal(blockAccess, pos, blockState) : 0;
    }

    @Override
    public int getStrongPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        return getWeakPower(blockState, blockAccess, pos, side);
    }

    @Override
    public int getActiveSignal(IBlockAccess worldIn, BlockPos pos, IBlockState state)
    {
        TileEntity tileEntity = worldIn.getTileEntity(pos);

        if(tileEntity instanceof TileEntityTFlipFlop)
        {
            TileEntityTFlipFlop entity = (TileEntityTFlipFlop)tileEntity;

            return entity.hasOutput() ? 15 : 0;
        }
        return 0;
    }

    @Override
    protected IBlockState getPoweredState(IBlockState unpoweredState)
    {
        EnumFacing facing = unpoweredState.getValue(FACING);
        return BlockInit.TFLIP_FLOP.getDefaultState().withProperty(FACING, facing);
    }

    @Override
    protected IBlockState getUnpoweredState(IBlockState poweredState)
    {
        EnumFacing facing = poweredState.getValue(FACING);
        return BlockInit.TFLIP_FLOP.getDefaultState().withProperty(FACING, facing);
    }

    public boolean eventReceived(IBlockState state, World worldIn, BlockPos pos, int id, int param)
    {
        super.eventReceived(state, worldIn, pos, id, param);
        TileEntity tileentity = worldIn.getTileEntity(pos);
        return tileentity != null && tileentity.receiveClientEvent(id, param);
    }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        this.onStateChange(worldIn, pos, state);
    }

    private void onStateChange(World worldIn, BlockPos pos, IBlockState state)
    {
        TileEntity tileEntity = worldIn.getTileEntity(pos);

        if(tileEntity instanceof TileEntityTFlipFlop)
        {
            TileEntityTFlipFlop entity = (TileEntityTFlipFlop)tileEntity;

            if(entity.isLocked() && calculateInputStrength(worldIn, pos, state) == 0)
            {
                entity.setLocked(false);
            }
            else if(!entity.isLocked() && calculateInputStrength(worldIn, pos, state) > 0)
            {
                entity.setOutput(!entity.hasOutput());
                entity.setLocked(true);
            }

            this.notifyNeighbors(worldIn, pos, state);
        }
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, FACING);
    }

    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta));
    }

    public int getMetaFromState(IBlockState state)
    {
        int i = 0;

        i |= state.getValue(FACING).getHorizontalIndex();

        return i;
    }

    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        super.onBlockAdded(worldIn, pos, state);
        worldIn.setTileEntity(pos, this.createNewTileEntity(worldIn, 0));
    }

    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityTFlipFlop();
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return ItemInit.TFLIP_FLOP_ITEM;
    }

    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        this.updateTick(worldIn, pos, state, worldIn.rand);
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
