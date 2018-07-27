package norg.neptunia.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import norg.neptunia.capability.CapProvider;
import norg.neptunia.capability.INepCapability;

public class EntityMagicBall extends EntityThrowable {

    private float damage;

    public EntityMagicBall(World worldIn) {
        super(worldIn);
    }

    public EntityMagicBall(World worldIn, EntityLivingBase throwerIn, float damage) {
        super(worldIn, throwerIn);
        this.damage = damage;
    }

    public EntityMagicBall(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    @Override
    protected float getGravityVelocity() {
        return 0.0F;
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (result.entityHit instanceof EntityMagicCircle) return;
        if (result.entityHit != null) {
            result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), this.damage);
            if (!this.world.isRemote && this.thrower instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) this.thrower;
                if (result.entityHit instanceof EntityLiving) {
                    INepCapability pc = CapProvider.get(player);
                    pc.setSuperPower(pc.getStatue(), pc.getSuperPower(pc.getStatue()) + 1);
                    pc.dataChanged(player);
                }
            }
        }
        if (!this.world.isRemote) {
            this.setDead();
        }
    }

}
