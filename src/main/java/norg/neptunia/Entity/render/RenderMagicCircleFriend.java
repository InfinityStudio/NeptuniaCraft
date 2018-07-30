package norg.neptunia.entity.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import norg.neptunia.entity.EntityMagicCircleFriend;
import norg.neptunia.entity.model.ModelMagicCircleBottom;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class RenderMagicCircleFriend extends Render<EntityMagicCircleFriend> {

    private static final ResourceLocation magicCircleFriend = new ResourceLocation("neptunia", "textures/entity/magic_circle_friend.png");
    private static final ModelBase model = new ModelMagicCircleBottom();

    public RenderMagicCircleFriend(RenderManager renderManager) {
        super(renderManager);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityMagicCircleFriend entity) {
        return magicCircleFriend;
    }

    @Override
    public void doRender(EntityMagicCircleFriend entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.pushMatrix();
        this.bindEntityTexture(entity);
        GlStateManager.translate((float)x, (float)y, (float)z);
        this.model.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.03125F);
        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

}
