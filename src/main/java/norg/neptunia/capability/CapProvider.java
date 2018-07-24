package norg.neptunia.capability;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * Created by The Sea on 2016/6/12.
 */
public class CapProvider implements ICapabilityProvider, INBTSerializable {

    @CapabilityInject(INepCapability.class)
    public static Capability<INepCapability> NEP_CAP = null;
    private INepCapability nepCapability;

    public CapProvider() {
        nepCapability = new NeptuniaCapability();
    }

    public CapProvider(INepCapability nepCapability) {
        this.nepCapability = nepCapability;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return NEP_CAP != null && capability == NEP_CAP;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (NEP_CAP != null && capability == NEP_CAP) return (T)nepCapability;
        return null;
    }

    public static INepCapability get(EntityPlayer player) {
        return player.hasCapability(NEP_CAP, null)? player.getCapability(NEP_CAP, null): null;
    }

    @Override
    public NBTBase serializeNBT() {
        return nepCapability.saveNBTData();
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        nepCapability.loadNBTData((NBTTagCompound) nbt);
    }
}
