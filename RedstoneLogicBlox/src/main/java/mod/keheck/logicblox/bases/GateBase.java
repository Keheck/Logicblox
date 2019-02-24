package mod.keheck.logicblox.bases;

import mod.keheck.logicblox.init.BlockInit;
import mod.keheck.logicblox.util.interfaces.markers.*;
import net.minecraft.block.*;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Random;

public abstract class GateBase extends BlockRedstoneDiode
{
    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
    public static final PropertyBool ACTIVE = PropertyBool.create("active");
    private static final AxisAlignedBB HITBOX = new AxisAlignedBB(0.0d, 0.0d, 0d, 1d, 1d, 1d);

    public GateBase(String name)
    {
        super(false);

        setUnlocalizedName(name);
        setRegistryName(name);
        setHarvestLevel("pickaxe", 2);
        setSoundType(SoundType.METAL);
        setBlockUnbreakable();

        if(this instanceof NoFacing)
        {
            setDefaultState(this.blockState.getBaseState().withProperty(ACTIVE, false));
        }
        else if(this instanceof ITileEntityProvider || this instanceof NoActiveState)
        {
            setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
        }
        else if(this instanceof NoBlockstate)
        {
            setDefaultState(this.blockState.getBaseState());
        }
        else
        {
            setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(ACTIVE, false));
        }

        BlockInit.BLOCKS.add(this);
    }

    /**
     * returns the power the block should give when activated
     */
    @Override
    public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        return blockState.getValue(FACING) == side && blockState.getValue(ACTIVE) ? this.getActiveSignal(blockAccess, pos, blockState) : 0;
    }

    /**
     * returns the same as the {@link #getWeakPower(IBlockState, IBlockAccess, BlockPos, EnumFacing)} method
     */
    @Override
    public int getStrongPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        return getWeakPower(blockState, blockAccess, pos, side);
    }

    /**
     * returns true if the block's
     * side can connect redstone
     */
    @Override
    public boolean canConnectRedstone(IBlockState state, IBlockAccess world, BlockPos pos, @Nullable EnumFacing side)
    {
        if(!(this instanceof NoFacing))
        {
            if(this instanceof TwoIn)
            {
                return !(side == state.getValue(FACING).getOpposite());
            }
            else if(this instanceof OneIn)
            {
                return side == state.getValue(FACING) || side == state.getValue(FACING).getOpposite();
            }
        }
        return false;
    }

    /**
     * creates a blockstate to make the mechanics possible
     */
    @Override
    protected BlockStateContainer createBlockState() { return new BlockStateContainer(this, FACING, ACTIVE); }

    /**
     * returns the corresponding blockstate from a given metadata
     */
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta>>1)).withProperty(ACTIVE, (meta & 1) == 1);
    }

    /**
     * returns the coresponding metadate from a given state
     */
    @Override
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;

        i |= state.getValue(FACING).getHorizontalIndex();
        i <<= 1;
        i |= state.getValue(ACTIVE) ? 1 : 0;

        return i;
    }

    /**
     * is called when the block is placed in the world
     */
    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        neighborChanged(state, worldIn, pos, this, pos);
        worldIn.notifyNeighborsOfStateChange(pos, worldIn.getBlockState(pos).getBlock(), true);
    }

    /**
     * returns the blockstate with the correct rotation
     */
    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }

    @Override
    public boolean isPowered(IBlockState state) { return state.getValue(ACTIVE); }

    @Override
    protected int getDelay(IBlockState state) { return 1; }

    /**
     * the side should allways be rendered
     */
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        return true;
    }

    @Override
    public abstract Item getItemDropped(IBlockState state, Random rand, int fortune);

    /**
     * returns an item stack containing the item aqquired by {@link #getItemDropped(IBlockState, Random, int)}
     */
    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(getItemDropped(state, null, 0));
    }

    /**
     * returns the accurate hitbox, since
     * the superclass' bounding box is too small
     */
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) { return HITBOX; }

    /**
     * every position is valid
     */
    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) { return true; }

    /**
     * the block won't get destroyed
     * no matter what happenes
     */
    @Override
    public boolean canBlockStay(World worldIn, BlockPos pos) { return true; }

    /**
     * every block is opaque
     */
    @Override
    public boolean isOpaqueCube(IBlockState state) { return true; }

    /*===============Abstact Methods===============*/

    /**
     * returns the powered state with every property
     * the previous state had when getting updated
     */
    @Override
    protected abstract IBlockState getPoweredState(IBlockState unpoweredState);

    /**
     * returns the unpowered state with every property
     * the previous state had when getting updated
     */
    @Override
    protected abstract IBlockState getUnpoweredState(IBlockState poweredState);
}
