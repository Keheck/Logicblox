package com.keheck.logicBlox.ModElements.TileEntitys;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBridgeOutput extends TileEntity
{
    private int power;

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        compound.setInteger("Power", power);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        power = compound.getInteger("Power");
        return compound;
    }

    public int getPower() { return power; }

    public void setPower(int power) { this.power = power; }
}
