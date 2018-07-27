package norg.neptunia.entity.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import norg.neptunia.entity.EntityMagicCircle;
import norg.neptunia.entity.model.ModelMagicCircle;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class RenderMagicCircle extends RenderLiving<EntityMagicCircle> {

    private static final ResourceLocation magicCircle = new ResourceLocation("neptunia", "textures/entity/magic_circle.png");

    public RenderMagicCircle(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelMagicCircle(), 0.0F);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityMagicCircle entity) {
        return magicCircle;
    }

}
