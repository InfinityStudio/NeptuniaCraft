package norg.neptunia.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMagicCircle extends ModelBase {

    public ModelRenderer shape1;

    public ModelMagicCircle() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.shape1 = new ModelRenderer(this, 0, 0);
        this.shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape1.addBox(-32.0F, -32.0F, 0.0F, 64, 64, 0, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.shape1.render(f5);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

}
