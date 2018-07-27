package norg.neptunia.item.weapon;

import net.minecraft.entity.Entity;
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

    private int curTick = 25;
    private boolean canUse = true;
    private float playerSpeed;

    public ItemWandNepgear() {
        super("nepgear_wand");
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
        if (entityIn instanceof EntityPlayer) {
            EntityPlayer entity = (EntityPlayer) entityIn;
            if (this.curTick >= 25) {
                this.canUse = true;
                if (entity.capabilities.getWalkSpeed() == 0.0F) entity.capabilities.setPlayerWalkSpeed(this.playerSpeed);
            }
            else this.curTick++;
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!worldIn.isRemote) {
            INepCapability pc = CapProvider.get(playerIn);
            if (this.canUse && pc.getWeapon(pc.getStatue()).getClass().isAssignableFrom(this.getClass())) {
                this.canUse = false;
                this.curTick = 0;
                this.playerSpeed = Math.max(this.playerSpeed, playerIn.capabilities.getWalkSpeed());
                playerIn.capabilities.setPlayerWalkSpeed(0.0F);
                playerIn.motionX = playerIn.motionY = playerIn.motionZ = 0.0F;
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
