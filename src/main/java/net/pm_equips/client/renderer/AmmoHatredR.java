package net.pm_equips.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.pm_equips.entity.EGOHatredMagicProjectile;

public class AmmoHatredR extends EntityRenderer<EGOHatredMagicProjectile> {
    public AmmoHatredR(EntityRendererProvider.Context context) {
        super(context);
        shadowRadius = 0.0F;
        shadowStrength = 0.0F;
    }

    @Override
    public void render(EGOHatredMagicProjectile entity, float yaw, float partialTicks,
                       PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        super.render(entity, yaw, partialTicks, poseStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(EGOHatredMagicProjectile entity) {
        return new ResourceLocation("minecraft", "textures/block/barrier.png");
    }
}
