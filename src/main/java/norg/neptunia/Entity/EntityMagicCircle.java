package norg.neptunia.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;

public class EntityMagicCircle extends EntityLiving {

    public EntityMagicCircle(World worldIn) {
        super(worldIn);
    }

    @Override
    public void onEntityUpdate() {
        super.onEntityUpdate();
        if (this.ticksExisted > 40) this.setDead();
    }

    @Override
    public boolean hasNoGravity() {
        return true;
    }

    @Override
    public boolean hitByEntity(Entity entityIn) {
        return true;
    }

    @Override
    public boolean attackable() {
        return false;
    }

}
