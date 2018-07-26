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
    protected ListCharacter listCharacter = new ListCharacter();

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
    public NBTTagCompound saveNBTData(EntityPlayer player) {
        NBTTagCompound compound = new NBTTagCompound();
        compound.setTag("Nep!", CapProvider.NEP_CAP.getStorage().writeNBT(CapProvider.NEP_CAP, CapProvider.get(player), null));
        return compound;
    }

    @Override
    public void loadNBTData(NBTTagCompound compound, EntityPlayer player) {
        CapProvider.NEP_CAP.getStorage().readNBT(CapProvider.NEP_CAP, CapProvider.get(player), null, compound.getTag("Nep!"));
    }

    @Override
    public void dataChanged(EntityPlayer player) {
        if(player != null){
            CommonProxy.network.sendTo(new MessageUpdateCap(saveNBTData(player)), (EntityPlayerMP) player);
        }
    }

    /*@Override
    public void dataChanged(EntityPlayerMP player) {
        if (player.hasCapability(CapProvider.NEP_CAP, null)) {
            MessageUpdateCap message = new MessageUpdateCap();
            INepCapability nepCapability = player.getCapability(CapProvider.NEP_CAP, null);
            Capability.IStorage<INepCapability> storage = CapProvider.NEP_CAP.getStorage();
            message.props = new NBTTagCompound();
            message.props.setTag("Nep!", storage.writeNBT(CapProvider.NEP_CAP, nepCapability, null));
            CommonProxy.network.sendTo(message, player);
        }
    }*/

    @Override
    public ListCharacter getListCharacter() {
        return listCharacter;
    }

    public static class CapStorage implements Capability.IStorage<INepCapability> {

        public static final CapStorage capStorage = new CapStorage();

        @Override
        public NBTBase writeNBT(Capability<INepCapability> capability, INepCapability instance, EnumFacing side) {
            NBTTagCompound compound = new NBTTagCompound();
            compound.setString("Statue", instance.getStatue());
            Iterator<ListCharacter.Information> iterator = instance.getListCharacter().characterList.iterator();
            while (iterator.hasNext()) {
                ListCharacter.Information chara = iterator.next();
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
            for (int i = 0; i < instance.getListCharacter().characterList.size(); i++) {
                String name = instance.getListCharacter().characterList.get(i).getName();
                NBTTagCompound props = compound.getCompoundTag(name);
                instance.setSuperPower(name, props.getInteger("SuperPower"));
                instance.setExEDriver(name, props.getInteger("ExEDriver"));
                instance.setLevel(name, props.getInteger("Level"));
                instance.setExp(name, props.getInteger("Exp"));
                instance.setMaxSP(name, props.getInteger("MaxSP"));
                instance.setMaxEXE(name, props.getInteger("MaxExE"));
                instance.setEXELevel(name, props.getInteger("ExELevel"));
                //instance.getInventory(name).readFromNBT(compound, instance.getListCharacter().characterList.get(i));
            }
        }
    }



    @Override
    public int getSuperPower(String statue) {
        Iterator<ListCharacter.Information> iterator = listCharacter.characterList.iterator();
        while (iterator.hasNext()) {
            ListCharacter.Information chara = iterator.next();
            if (chara.getName().equals(statue)) { return chara.getSuperPower(); }
        }
        return 0;
    }

    @Override
    public int getExEDriver(String statue) {
        Iterator<ListCharacter.Information> iterator = listCharacter.characterList.iterator();
        while (iterator.hasNext()) {
            ListCharacter.Information chara = iterator.next();
            if (chara.getName().equals(statue)) { return chara.getExeDriver(); }
        }
        return 0;
    }

    @Override
    public int getLevel(String statue) {
        Iterator<ListCharacter.Information> iterator = listCharacter.characterList.iterator();
        while (iterator.hasNext()) {
            ListCharacter.Information chara = iterator.next();
            if (chara.getName().equals(statue)) return chara.getLevel();
        }
        return 0;
    }

    @Override
    public int getMaxSP(String statue) {
        Iterator<ListCharacter.Information> iterator = listCharacter.characterList.iterator();
        while (iterator.hasNext()) {
            ListCharacter.Information chara = iterator.next();
            if (chara.getName().equals(statue)) return chara.getMaxSP();
        }
        return 0;
    }

    @Override
    public int getMaxExEDriver(String statue) {
        Iterator<ListCharacter.Information> iterator = listCharacter.characterList.iterator();
        while (iterator.hasNext()) {
            ListCharacter.Information chara = iterator.next();
            if (chara.getName().equals(statue)) return chara.getMaxDriver();
        }
        return 0;
    }

    @Override
    public int getExp(String statue) {
        Iterator<ListCharacter.Information> iterator = listCharacter.characterList.iterator();
        while (iterator.hasNext()) {
            ListCharacter.Information chara = iterator.next();
            if (chara.getName().equals(statue)) return chara.getExp();
        }
        return 0;
    }

    @Override
    public int getEXELevel(String statue) {
        Iterator<ListCharacter.Information> iterator = listCharacter.characterList.iterator();
        while (iterator.hasNext()) {
            ListCharacter.Information chara = iterator.next();
            if (chara.getName().equals(statue)) return chara.getExeLevel();
        }
        return 0;
    }

    @Override
    public Item getWeapon(String statue) {
        Iterator<ListCharacter.Information> iterator = listCharacter.characterList.iterator();
        while (iterator.hasNext()) {
            ListCharacter.Information chara = iterator.next();
            if (chara.getName().equals(statue)) return chara.getWeapon();
        }
        return null;
    }

    @Override
    public void setSuperPower(String statue, int sp) {
        for (int i = 0; i < listCharacter.characterList.size(); i++) {
            if (listCharacter.characterList.get(i).getName().equals(statue) && sp <= listCharacter.characterList.get(i).getMaxSP()) {
                listCharacter.characterList.get(i).setSuperPower(sp);
            }
        }
    }

    @Override
    public void setExEDriver(String statue, int exe) {
        for (int i = 0; i < listCharacter.characterList.size(); i++) {
            if (listCharacter.characterList.get(i).getName().equals(statue) && exe <= listCharacter.characterList.get(i).getMaxDriver()) {
                listCharacter.characterList.get(i).setExeDriver(exe);
            }
        }
    }

    @Override
    public void setLevel(String statue, int level) {
        for (int i = 0; i < listCharacter.characterList.size(); i++) {
            if (listCharacter.characterList.get(i).getName().equals(statue)) {
                listCharacter.characterList.get(i).setLevel(level);
            }
        }
    }

    @Override
    public void setExp(String statue, int exp) {
        for (int i = 0; i < listCharacter.characterList.size(); i++) {
            if (listCharacter.characterList.get(i).getName().equals(statue)) {
                listCharacter.characterList.get(i).setExp(exp);
            }
        }
    }

    @Override
    public void setMaxSP(String statue, int maxSP) {
        for (int i = 0; i < listCharacter.characterList.size(); i++) {
            if (listCharacter.characterList.get(i).getName().equals(statue)) {
                listCharacter.characterList.get(i).setMaxSP(maxSP);
            }
        }
    }

    @Override
    public void setMaxEXE(String statue, int maxEXE) {
        for (int i = 0; i < listCharacter.characterList.size(); i++) {
            if (listCharacter.characterList.get(i).getName().equals(statue)) {
                listCharacter.characterList.get(i).setMaxDriver(maxEXE);
            }
        }
    }

    @Override
    public void setEXELevel(String statue, int exeLevel) {
        for (int i = 0; i < listCharacter.characterList.size(); i++) {
            if (listCharacter.characterList.get(i).getName().equals(statue)) {
                listCharacter.characterList.get(i).setExeLevel(exeLevel);
            }
        }
    }

    @Override
    public InventoryExtended getInventory(String statue) {
        Iterator<ListCharacter.Information> iterator = listCharacter.characterList.iterator();
        while (iterator.hasNext()) {
            ListCharacter.Information chara = iterator.next();
            if (chara.getName().equals(statue)) return chara.getInventory();
        }
        return null;
    }


    @Override
    public void updateLevel(String statue) {
        Iterator<ListCharacter.Information> iterator = listCharacter.characterList.iterator();
        while (iterator.hasNext()) {
            ListCharacter.Information chara = iterator.next();
            if (chara.getName().equals(statue)) {
                int needExp = (int)Math.pow(2, chara.getLevel()/10)*100;
                while (chara.getExp() >= needExp && chara.getLevel() < 100) {
                    chara.setExp(chara.getExp()-needExp);
                    chara.setLevel(chara.getLevel()+1);
                }
            }
        }
    }
}