package net.lobotomy_corporation_mod.client.models;

import net.lobotomy_corporation_mod.entity.a5_t0346;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class a5m_t0346 extends GeoModel<a5_t0346> {
    @Override
    public ResourceLocation getModelResource(a5_t0346 animatable) {
        return new ResourceLocation("lobotomy_corporation_mod", "geo/m5_whitenight.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(a5_t0346 animatable) {
        return new ResourceLocation("lobotomy_corporation_mod", "textures/entity/m5_whitenight.png");
    }

    @Override
    public ResourceLocation getAnimationResource(a5_t0346 animatable) {
        return new ResourceLocation("lobotomy_corporation_mod", "animations/m5_whitenight.animation.json");
    }
}
