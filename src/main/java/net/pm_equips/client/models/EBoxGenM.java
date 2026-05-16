package net.pm_equips.client.models;

import net.pm_equips.blockentity.EBoxGenBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EBoxGenM extends GeoModel<EBoxGenBlockEntity> {
    @Override
    public ResourceLocation getModelResource(EBoxGenBlockEntity animatable) {
        return new ResourceLocation("pm_equips", "geo/e_gen_v2.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EBoxGenBlockEntity animatable) {
        return new ResourceLocation("pm_equips", "textures/block/e_gen.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EBoxGenBlockEntity animatable) {
        return new ResourceLocation("pm_equips", "animations/e_gen_v2.animation.json");
    }
}

