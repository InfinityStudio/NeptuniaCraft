package norg.neptunia.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import norg.neptunia.inventory.slot.SlotCustom;

/**
 * Created by The Sea on 2016/4/29.
 */
public class ContainerCharacterInfo extends Container {

    private final InventoryExtended inventory;
    private static final int numRows = 1;

    public ContainerCharacterInfo(EntityPlayer player, InventoryPlayer inventoryPlayer, InventoryExtended inventoryExtended) {
        this.inventory = inventoryExtended;
        for (int i = 0; i < inventoryExtended.getSizeInventory(); ++i) {
            this.addSlotToContainer(new SlotCustom(inventoryExtended, i, 35 + i * 18, 114));
        }
        for (int l = 0; l < 3; ++l) {
            for (int j1 = 0; j1 < 9; ++j1) {
                this.addSlotToContainer(new Slot(inventoryPlayer, j1 + (l + 1) * 9, 35 + j1 * 18, 136 + l * 18));
            }
        }
        for (int i1 = 0; i1 < 9; ++i1) {
            this.addSlotToContainer(new Slot(inventoryPlayer, i1, 35 + i1 * 18, 194));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return this.inventory.isUsableByPlayer(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (index < this.numRows * 9) {
                if (!this.mergeItemStack(itemstack1, this.numRows * 9, this.inventorySlots.size(), true)) {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, this.numRows * 9, false)) {
                return null;
            }
            if (itemstack1.getMaxStackSize() == 0) {
                slot.putStack((ItemStack)null);
            }
            else {
                slot.onSlotChanged();
            }
        }
        return itemstack;
    }
}
