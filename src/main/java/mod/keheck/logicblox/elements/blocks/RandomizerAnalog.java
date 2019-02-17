package mod.keheck.logicblox.elements.blocks;

import mod.keheck.logicblox.bases.GateBase;
import mod.keheck.logicblox.init.BlockInit;
import mod.keheck.logicblox.init.ItemInit;
import mod.keheck.logicblox.elements.tileentities.TileEntityRandomizerAnalog;
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

public class RandomizerAnalog extends GateBase implements ITileEntityProvider, OneIn
{

    public RandomizerAnalog(String name) { super(name); }

    @Override
    public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        return blockState.getValue(FACING) == side ? this.getActiveSignal(blockAccess, pos, blockState) : 0;
    }

    @Override
    public int getActiveSignal(IBlockAccess worldIn, BlockPos pos, IBlockState state)
    {
        TileEntity entity = worldIn.getTileEntity(pos);

        return entity instanceof TileEntityRandomizerAnalog ? ((TileEntityRandomizerAnalog)entity).getOutputSignal() : 0;
    }

    @Override
    protected IBlockState getPoweredState(IBlockState unpoweredState)
    {
        EnumFacing facing = unpoweredState.getValue(FACING);
        return BlockInit.RANDOMIZER_ANALOG.getDefaultState().withProperty(FACING, facing);
    }

    @Override
    protected IBlockState getUnpoweredState(IBlockState poweredState)
    {
        EnumFacing facing = poweredState.getValue(FACING);
        return BlockInit.RANDOMIZER_ANALOG.getDefaultState().withProperty(FACING, facing);
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

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        int out = rand.nextInt(15)+1;

        if (this.isRepeaterPowered && out == 0)
        {
            worldIn.setBlockState(pos, this.getUnpoweredState(state), 4);
        }

        this.onStateChange(worldIn, pos, state, out);
    }

    private void onStateChange(World worldIn, BlockPos pos, IBlockState state, int output)
    {
        TileEntity tileEntity = worldIn.getTileEntity(pos);

        if(tileEntity instanceof TileEntityRandomizerAnalog)
        {
            TileEntityRandomizerAnalog entity = (TileEntityRandomizerAnalog)tileEntity;

            if(entity.isLocked() && this.calculateInputStrength(worldIn, pos, state) == 0)
            {
                entity.lock(false);
                entity.setOutputSignal(0);
            }
            else if(!entity.isLocked() && this.calculateInputStrength(worldIn, pos, state) > 0 && output > 0)
            {
                entity.lock(true);
                entity.setOutputSignal(output);
            }
            else if(this.calculateInputStrength(worldIn, pos, state) > 0 && output == 0 && !entity.isLocked())
            {
                entity.lock(true);
                entity.setOutputSignal(output);
            }

            this.notifyNeighbors(worldIn, pos, state);
        }
    }

    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        super.onBlockAdded(worldIn, pos, state);
        worldIn.setTileEntity(pos, this.createNewTileEntity(worldIn, 0));
    }

    public TileEntity createNewTileEntity(World worldIn, int meta) { return new TileEntityRandomizerAnalog(); }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) { return ItemInit.RANDOMIZER_ANALOG_ITEM; }

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
