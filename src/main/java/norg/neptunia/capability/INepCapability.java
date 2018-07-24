package norg.neptunia.capability;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import norg.neptunia.inventory.InventoryExtended;

/**
 * Created by The Sea on 2016/6/12.
 */
public interface INepCapability {
    String getStatue();
    void setStatue(String newStatue);
    NBTTagCompound saveNBTData();
    void loadNBTData(NBTTagCompound compound);
    void dataChanged(EntityPlayer player);
    ListCharactor getListCharactor();
    int getSuperPower(String statue);
    int getExEDriver(String statue);
    int getLevel(String statue);
    int getMaxSP(String statue);
    int getMaxExEDriver(String statue);
    int getExp(String statue);
    Item getWeapon(String statue);
    void setSuperPower(String statue, int sp);
    void setExEDriver(String statue, int exe);
    void setLevel(String statue, int level);
    void setExp(String statue, int exp);
    void setMaxSP(String statue, int maxSP);
    void setMaxEXE(String statue, int maxEXE);
    void setBelief(int belief);
    InventoryExtended getInventory(String statue);
}
