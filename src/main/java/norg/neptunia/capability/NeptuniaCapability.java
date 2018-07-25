package norg.neptunia.capability;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import norg.neptunia.inventory.InventoryExtended;
import norg.neptunia.network.MessageUpdateCap;
import norg.neptunia.proxy.CommonProxy;

import java.util.Iterator;

/**
 * Created by The Sea on 2016/6/12.
 */
public class NeptuniaCapability implements INepCapability {

    protected String statue = "Neptune";
    protected int belief = 0;
    protected ListCharactor listCharactor = new ListCharactor();

    public NeptuniaCapability() { }

    @Override
    public String getStatue() {
        return statue;
    }

    @Override
    public void setStatue(String newStatue) {
        statue = newStatue;
    }

    @Override
    public NBTTagCompound saveNBTData() {
        return (NBTTagCompound) CapStorage.capStorge.writeNBT(CapProvider.NEP_CAP, this, null);
    }

    @Override
    public void loadNBTData(NBTTagCompound compound) {
        CapStorage.capStorge.readNBT(CapProvider.NEP_CAP, this, null, compound);
    }

    @Override
    public void dataChanged(EntityPlayer player) {
        if(player != null){
            CommonProxy.network.sendTo(new MessageUpdateCap(saveNBTData()), (EntityPlayerMP) player);
        }
    }

    @Override
    public ListCharactor getListCharactor() {
        return listCharactor;
    }

    public static class CapStorage implements Capability.IStorage<INepCapability> {
        public static final CapStorage capStorge = new CapStorage();

        @Override
        public NBTBase writeNBT(Capability<INepCapability> capability, INepCapability instance, EnumFacing side) {
            NBTTagCompound compound = new NBTTagCompound();
            compound.setString("Statue", instance.getStatue());
            Iterator<ListCharactor.Information> iterator = instance.getListCharactor().charactorList.iterator();
            while (iterator.hasNext()) {
                ListCharactor.Information chara = iterator.next();
                NBTTagCompound props = new NBTTagCompound();
                props.setInteger("SuperPower", chara.getSuperPower());
                props.setInteger("MaxSP", chara.getMaxSP());
                props.setInteger("ExEDriver", chara.getExeDriver());
                props.setInteger("MaxExE", chara.getMaxDriver());
                props.setInteger("Level", chara.getLevel());
                props.setInteger("Exp", chara.getExp());
                props.setInteger("ExELevel", chara.getExeLevel());
                //props = chara.getInventory().writeToNBT(compound, chara);
                compound.setTag(chara.getName(), props);
            }
             return compound;
        }

        @Override
        public void readNBT(Capability<INepCapability> capability, INepCapability instance, EnumFacing side, NBTBase nbt) {
            NBTTagCompound compound = (NBTTagCompound) nbt;
            instance.setStatue(compound.getString("Statue"));
            for (int i = 0; i < instance.getListCharactor().charactorList.size(); i++) {
                String name = instance.getListCharactor().charactorList.get(i).getName();
                NBTTagCompound props = compound.getCompoundTag(name);
                instance.setSuperPower(name, props.getInteger("SuperPower"));
                instance.setExEDriver(name, props.getInteger("ExEDriver"));
                instance.setLevel(name, props.getInteger("Level"));
                instance.setExp(name, props.getInteger("Exp"));
                instance.setMaxSP(name, props.getInteger("MaxSP"));
                instance.setMaxEXE(name, props.getInteger("MaxExE"));
                instance.setEXELevel(name, props.getInteger("ExELevel"));
                //instance.getInventory(name).readFromNBT(compound, instance.getListCharactor().charactorList.get(i));
            }
        }
    }

    @Override
    public int getSuperPower(String statue) {
        Iterator<ListCharactor.Information> iterator = listCharactor.charactorList.iterator();
        while (iterator.hasNext()) {
            ListCharactor.Information chara = iterator.next();
            if (chara.getName().equals(statue)) { return chara.getSuperPower(); }
        }
        return 0;
    }

    @Override
    public int getExEDriver(String statue) {
        Iterator<ListCharactor.Information> iterator = listCharactor.charactorList.iterator();
        while (iterator.hasNext()) {
            ListCharactor.Information chara = iterator.next();
            if (chara.getName().equals(statue)) { return chara.getExeDriver(); }
        }
        return 0;
    }

    @Override
    public int getLevel(String statue) {
        Iterator<ListCharactor.Information> iterator = listCharactor.charactorList.iterator();
        while (iterator.hasNext()) {
            ListCharactor.Information chara = iterator.next();
            if (chara.getName().equals(statue)) return chara.getLevel();
        }
        return 0;
    }

    @Override
    public int getMaxSP(String statue) {
        Iterator<ListCharactor.Information> iterator = listCharactor.charactorList.iterator();
        while (iterator.hasNext()) {
            ListCharactor.Information chara = iterator.next();
            if (chara.getName().equals(statue)) return chara.getMaxSP();
        }
        return 0;
    }

    @Override
    public int getMaxExEDriver(String statue) {
        Iterator<ListCharactor.Information> iterator = listCharactor.charactorList.iterator();
        while (iterator.hasNext()) {
            ListCharactor.Information chara = iterator.next();
            if (chara.getName().equals(statue)) return chara.getMaxDriver();
        }
        return 0;
    }

    @Override
    public int getExp(String statue) {
        Iterator<ListCharactor.Information> iterator = listCharactor.charactorList.iterator();
        while (iterator.hasNext()) {
            ListCharactor.Information chara = iterator.next();
            if (chara.getName().equals(statue)) return chara.getExp();
        }
        return 0;
    }

    @Override
    public int getEXELevel(String statue) {
        Iterator<ListCharactor.Information> iterator = listCharactor.charactorList.iterator();
        while (iterator.hasNext()) {
            ListCharactor.Information chara = iterator.next();
            if (chara.getName().equals(statue)) return chara.getExeLevel();
        }
        return 0;
    }

    @Override
    public Item getWeapon(String statue) {
        Iterator<ListCharactor.Information> iterator = listCharactor.charactorList.iterator();
        while (iterator.hasNext()) {
            ListCharactor.Information chara = iterator.next();
            if (chara.getName().equals(statue)) return chara.getWeapon();
        }
        return null;
    }

    @Override
    public void setSuperPower(String statue, int sp) {
        for (int i = 0; i < listCharactor.charactorList.size(); i++) {
            if (listCharactor.charactorList.get(i).getName().equals(statue) && sp < listCharactor.charactorList.get(i).getMaxSP()) {
                listCharactor.charactorList.get(i).setSuperPower(sp);
            }
        }
    }

    @Override
    public void setExEDriver(String statue, int exe) {
        for (int i = 0; i < listCharactor.charactorList.size(); i++) {
            if (listCharactor.charactorList.get(i).getName().equals(statue) && exe < listCharactor.charactorList.get(i).getMaxDriver()) {
                listCharactor.charactorList.get(i).setExeDriver(exe);
            }
        }
    }

    @Override
    public void setLevel(String statue, int level) {
        for (int i = 0; i < listCharactor.charactorList.size(); i++) {
            if (listCharactor.charactorList.get(i).getName().equals(statue)) {
                listCharactor.charactorList.get(i).setLevel(level);
            }
        }
    }

    @Override
    public void setExp(String statue, int exp) {
        for (int i = 0; i < listCharactor.charactorList.size(); i++) {
            if (listCharactor.charactorList.get(i).getName().equals(statue)) {
                listCharactor.charactorList.get(i).setExp(exp);
            }
        }
    }

    @Override
    public void setMaxSP(String statue, int maxSP) {
        for (int i = 0; i < listCharactor.charactorList.size(); i++) {
            if (listCharactor.charactorList.get(i).getName().equals(statue)) {
                listCharactor.charactorList.get(i).setMaxSP(maxSP);
            }
        }
    }

    @Override
    public void setMaxEXE(String statue, int maxEXE) {
        for (int i = 0; i < listCharactor.charactorList.size(); i++) {
            if (listCharactor.charactorList.get(i).getName().equals(statue)) {
                listCharactor.charactorList.get(i).setMaxDriver(maxEXE);
            }
        }
    }

    @Override
    public void setEXELevel(String statue, int exeLevel) {
        for (int i = 0; i < listCharactor.charactorList.size(); i++) {
            if (listCharactor.charactorList.get(i).getName().equals(statue)) {
                listCharactor.charactorList.get(i).setExeLevel(exeLevel);
            }
        }
    }

    @Override
    public InventoryExtended getInventory(String statue) {
        Iterator<ListCharactor.Information> iterator = listCharactor.charactorList.iterator();
        while (iterator.hasNext()) {
            ListCharactor.Information chara = iterator.next();
            if (chara.getName().equals(statue)) return chara.getInventory();
        }
        return null;
    }
}