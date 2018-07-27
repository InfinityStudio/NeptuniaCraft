package norg.neptunia.capability;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

/**
 * Created by The Sea on 2016/6/12.
 */
public class CapProvider implements ICapabilitySerializable<NBTTagCompound> {

    @CapabilityInject(INepCapability.class)
    public static Capability<INepCapability> NEP_CAP;

    private INepCapability nepCapability = new NeptuniaCapability();

    public CapProvider() { }

    public CapProvider(INepCapability nepCapability) {
        this.nepCapability = nepCapability;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return NEP_CAP.equals(capability);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (NEP_CAP.equals(capability)) {
            @SuppressWarnings("unchecked")
                    T result = (T) nepCapability;
            return result;
        }
        return null;
    }

    public static INepCapability get(EntityPlayer player) {
        return player.hasCapability(NEP_CAP, null)? player.getCapability(NEP_CAP, null): null;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound compound = new NBTTagCompound();
        compound.setTag("Nep!", NEP_CAP.getStorage().writeNBT(NEP_CAP, nepCapability, null));
        return compound;
    }

    @Override
    public void deserializeNBT(NBTTagCompound compound) {
        NBTBase nbt = compound.getTag("Nep!");
        NEP_CAP.getStorage().readNBT(NEP_CAP, nepCapability, null, nbt);
    }
}