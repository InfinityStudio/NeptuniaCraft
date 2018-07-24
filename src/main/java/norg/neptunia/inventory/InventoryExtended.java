package norg.neptunia.inventory;

import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.NonNullList;
import norg.neptunia.capability.ListCharactor.*;

/**
 * Created by The Sea on 2016/4/30.
 */
public class InventoryExtended extends InventoryBasic {

    public InventoryExtended() {
        super("Neptunia Inventory", true, 9);
    }


    public NBTTagCompound writeToNBT(NBTTagCompound compound, Information chara) {
        NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < this.getSizeInventory(); ++i) {
            ItemStack itemstack = this.getStackInSlot(i);
            if (itemstack != null) {
                NBTTagCompound nbttagcompound = new NBTTagCompound();
                nbttagcompound.setByte("Slot", (byte)i);
                itemstack.writeToNBT(nbttagcompound);
                nbttaglist.appendTag(nbttagcompound);
            }
        }
        compound.setTag(chara.getName() + "_slot", nbttaglist);
        return compound;
    }

    public void readFromNBT(NBTTagCompound compound, Information chara) {
        NBTTagList nbttaglist = compound.getTagList(chara.getName() + "_slot", compound.getId());
        for (int k = 0; k < nbttaglist.tagCount(); ++k) {
            NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(k);
            int j = nbttagcompound.getByte("Slot");
            if (j >= 0 && j < this.getSizeInventory()) {
                NonNullList<ItemStack> tmp = NonNullList.<ItemStack>withSize(1, ItemStack.EMPTY);
                ItemStackHelper.loadAllItems(nbttagcompound, tmp);
                this.setInventorySlotContents(j, tmp.get(0));
            }
        }
    }
}
