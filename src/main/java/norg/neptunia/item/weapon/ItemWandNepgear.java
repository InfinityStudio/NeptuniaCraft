package norg.neptunia.item.weapon;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import norg.neptunia.Entity.EntityMagicBall;
import norg.neptunia.capability.CapProvider;
import norg.neptunia.capability.INepCapability;
import norg.neptunia.item.ItemBasic;

public class ItemWandNepgear extends ItemBasic {

    public ItemWandNepgear() {
        super("nepgear_wand");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!worldIn.isRemote) {
            INepCapability pc = CapProvider.get(playerIn);
            if (pc.getWeapon(pc.getStatue()).getClass().isAssignableFrom(this.getClass())) {
                EntityMagicBall entity = new EntityMagicBall(worldIn, playerIn, 10.0F);
                entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 0.0F);
                worldIn.spawnEntity(entity);
            }
        }
        return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
    }

}
