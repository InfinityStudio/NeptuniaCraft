package norg.neptunia.entity;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityMagicCircle extends Entity {

    public EntityMagicCircle(World world) {
        super(world);
    }

    public EntityMagicCircle(World worldIn, double x, double y, double z, float yaw, float pitch) {
        super(worldIn);
        setPosition(x, y, z);
        setRotation(yaw, pitch);
    }

    @Override
    protected void entityInit() { }

    @Override
    public void onEntityUpdate() {
        super.onEntityUpdate();
        if (this.ticksExisted > 40) this.setDead();
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {

    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {

    }

}
