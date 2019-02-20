package mod.keheck.logicblox.elements.blocks.gates;

import mod.keheck.logicblox.bases.GateBase;
import mod.keheck.logicblox.init.BlockInit;
import mod.keheck.logicblox.init.ItemInit;
import mod.keheck.logicblox.util.interfaces.markers.OneIn;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BlockNot extends GateBase implements OneIn
{
    public BlockNot(String name) { super(name); }

    /**
     * @see GateBase#getPoweredState(IBlockState)
     */
    @Override
    protected IBlockState getPoweredState(IBlockState unpoweredState)
    {
        EnumFacing facing = unpoweredState.getValue(FACING);
        return BlockInit.GATE_NOT.getDefaultState().withProperty(FACING, facing).withProperty(ACTIVE, true);
    }

    /**
     * @see GateBase#getUnpoweredState(IBlockState)
     */
    @Override
    protected IBlockState getUnpoweredState(IBlockState poweredState)
    {
        EnumFacing facing = poweredState.getValue(FACING);
        return BlockInit.GATE_NOT.getDefaultState().withProperty(FACING, facing).withProperty(ACTIVE, false);
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

    /**
     * @see GateBase#getItemDropped(IBlockState, Random, int)
     */
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) { return ItemInit.ITEM_GATE_NOT; }
}
