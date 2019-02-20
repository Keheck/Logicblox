package mod.keheck.logicblox.elements.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityRandomizerAnalog extends TileEntity
{
    private int outputSignal;
    private boolean locked;

    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setInteger("OutputSignal", this.outputSignal);
        compound.setBoolean("Locked", this.locked);
        return compound;
    }

    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.outputSignal = compound.getInteger("OutputSignal");
        this.locked = compound.getBoolean("Locked");
    }

    public int getOutputSignal() { return this.outputSignal; }

    public void setOutputSignal(int outputSignalIn) { this.outputSignal = outputSignalIn; }

    public void lock(boolean locked) { this.locked = locked; }

    public boolean isLocked() { return locked; }
}
