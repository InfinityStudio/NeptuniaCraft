package norg.neptunia.entity;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import norg.neptunia.NeptuniaCraft;
import norg.neptunia.entity.render.RenderMagicCircle;

public class EntityLoader {

    private static int id = 0;

    public EntityLoader() {
        String basicLocation = "textures/entity/";
        registerEntity(basicLocation+"magic_ball", EntityMagicBall.class, "magic_ball", 64, 3, true);
        registerEntity(basicLocation+"magic_circle", EntityMagicCircle.class, "magic_circle", 64, 3, false);
    }

    private static void registerEntity(String resourceLocation, Class<? extends Entity> entityClass, String name, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates) {
        EntityRegistry.registerModEntity(new ResourceLocation("neptunia", resourceLocation), entityClass, name, id++, NeptuniaCraft.instance, trackingRange, updateFrequency, sendsVelocityUpdates);
    }

    @SideOnly(Side.CLIENT)
    public static void registerRender() {
        registerEntityRender(EntityMagicCircle.class, RenderMagicCircle.class);
    }

    @SideOnly(Side.CLIENT)
    public static <T extends Entity> void registerEntityRender(Class<T> entityClass, Class<? extends Render<T>> render) {
        RenderingRegistry.registerEntityRenderingHandler(entityClass, new EntityRenderFactory<T>(render));
    }

}
