package com.keheck.logicBlox.ModElements.TileEntitys;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityRandomizerDigital extends TileEntity
{
    private boolean outputSignal;
    private boolean locked;

    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setBoolean("OutputSignal", this.outputSignal);
        compound.setBoolean("Locked", this.locked);
        return compound;
    }

    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.outputSignal = compound.getBoolean("OutputSignal");
        this.locked = compound.getBoolean("Locked");
    }

    public boolean getOutputSignal()
    {
        return this.outputSignal;
    }

    public void setOutputSignal(boolean outputSignalIn)
    {
        this.outputSignal = outputSignalIn;
    }

    public void lock(boolean locked)
    {
        this.locked = locked;
    }

    public boolean isLocked()
    {
        return locked;
    }
}
