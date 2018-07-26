package norg.neptunia.Entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.entity.Entity;

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
        if (result.entityHit != null) {
            result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), this.damage);
        }
        if (!this.world.isRemote) {
            //this.world.setEntityState(this, (byte)3);
            this.setDead();
        }
    }

}
