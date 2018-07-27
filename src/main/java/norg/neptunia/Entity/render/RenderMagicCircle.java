package norg.neptunia.entity.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import norg.neptunia.entity.EntityMagicCircle;
import norg.neptunia.entity.model.ModelMagicCircle;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class RenderMagicCircle extends Render<EntityMagicCircle> {

    private static final ResourceLocation magicCircle = new ResourceLocation("neptunia", "textures/entity/magic_circle.png");
    private static final ModelBase model = new ModelMagicCircle();

    public RenderMagicCircle(RenderManager renderManager) {
        super(renderManager);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityMagicCircle entity) {
        return magicCircle;
    }

    @Override
    public void doRender(EntityMagicCircle entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.pushMatrix();
        this.bindEntityTexture(entity);
        GlStateManager.translate((float)x, (float)y, (float)z);
        GlStateManager.rotate(180.0F - entityYaw, 0.0F, 1.0F, 0.0F);
        this.model.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.03125F);
        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

}
