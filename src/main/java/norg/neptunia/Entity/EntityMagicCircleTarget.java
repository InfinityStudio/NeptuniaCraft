package norg.neptunia.entity;

import net.minecraft.world.World;

public class EntityMagicCircleTarget extends EntityMagicCircle {

    public EntityMagicCircleTarget(World world) {
        super(world);
    }

    public EntityMagicCircleTarget(World worldIn, double x, double y, double z, float yaw, float pitch) {
        super(worldIn, x, y, z, yaw, pitch);
    }

}
