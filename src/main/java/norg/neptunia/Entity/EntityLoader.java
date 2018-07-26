package norg.neptunia.Entity;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraft.entity.Entity;
import norg.neptunia.NeptuniaCraft;

public class EntityLoader {

    private static int id = 0;

    public EntityLoader() {
        String basicLocation = "textures/entity/";
        registerEntity(basicLocation+"magic_ball", EntityMagicBall.class, "magic_ball", 64, 3, true);
    }

    private static void registerEntity(String resourceLocation, Class<? extends Entity> entityClass, String name, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates) {
        EntityRegistry.registerModEntity(new ResourceLocation("neptunia", resourceLocation), entityClass, name, id++, NeptuniaCraft.instance, trackingRange, updateFrequency, sendsVelocityUpdates);
    }

}
