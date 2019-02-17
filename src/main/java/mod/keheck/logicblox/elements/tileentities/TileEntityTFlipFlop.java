package mod.keheck.logicblox.elements.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityTFlipFlop extends TileEntity
{
    private boolean locked;
    private boolean output;

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setBoolean("Locked", locked);
        compound.setBoolean("Output", output);
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        locked = compound.getBoolean("Locked");
        output = compound.getBoolean("Output");
    }

    public void setLocked(boolean locked) { this.locked = locked; }

    public boolean isLocked() { return locked; }

    public void setOutput(boolean output) { this.output = output; }

    public boolean hasOutput() { return output; }
}
