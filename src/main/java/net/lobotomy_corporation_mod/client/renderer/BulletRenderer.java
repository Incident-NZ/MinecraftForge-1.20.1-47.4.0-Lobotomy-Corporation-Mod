package net.lobotomy_corporation_mod.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.lobotomy_corporation_mod.entity.BulletEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class BulletRenderer extends EntityRenderer<BulletEntity> {

    public BulletRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0.0F;
        this.shadowStrength = 0.0F;
    }

    @Override
    public ResourceLocation getTextureLocation(BulletEntity p_114482_) {
        return new ResourceLocation("minecraft", "textures/block/barrier.png");
    }

    @Override
    public void render(BulletEntity entity, float yaw, float partialTicks,
                       PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        super.render(entity, yaw, partialTicks, poseStack, buffer, packedLight);
    }
}
