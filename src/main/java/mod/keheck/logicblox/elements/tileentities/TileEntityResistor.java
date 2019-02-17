package mod.keheck.logicblox.elements.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityResistor extends TileEntity
{
    private int resistance;
    private int input;
    private boolean locked;

    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setInteger("Resistance", this.resistance);
        compound.setInteger("Input", this.input);
        compound.setBoolean("Locked", this.locked);
        return compound;
    }

    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.resistance = compound.getInteger("Resistance");
        this.input = compound.getInteger("Input");
        this.locked = compound.getBoolean("Locked");
    }

    public void setResistance(int resistance)
    {
        if(resistance > 0 && !this.locked)
        {
            this.resistance = resistance / 2;
            locked = true;
        }
    }

    public void setInput(int input)
    {
        this.input = input;
    }

    public int getOutput()
    {
        return this.input - this.resistance < 0 || input == 0 ? 0 : this.input - this.resistance;
    }
}
