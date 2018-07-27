package norg.neptunia.item.weapon;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import norg.neptunia.entity.EntityMagicBall;
import norg.neptunia.entity.EntityMagicCircle;
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
                EntityMagicBall entityMagicBall = new EntityMagicBall(worldIn, playerIn, 10.0F);
                entityMagicBall.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 0.0F);
                worldIn.spawnEntity(entityMagicBall);
                EntityMagicCircle entityMagicCircle = new EntityMagicCircle(worldIn, playerIn.posX-1.5*MathHelper.sin((180-playerIn.rotationYaw)*0.017453292F),
                        playerIn.posY+playerIn.getEyeHeight(), playerIn.posZ-1.5*MathHelper.cos((180-playerIn.rotationYaw)*0.017453292F),
                        playerIn.rotationYaw, playerIn.rotationPitch);
                worldIn.spawnEntity(entityMagicCircle);
            }
        }
        return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
    }

}
