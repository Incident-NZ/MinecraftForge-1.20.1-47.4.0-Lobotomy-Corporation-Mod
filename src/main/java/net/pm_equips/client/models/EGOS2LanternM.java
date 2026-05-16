package net.pm_equips.client.models;

import net.pm_equips.items.EGOS2Lantern;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EGOS2LanternM extends GeoModel<EGOS2Lantern> {
    @Override
    public ResourceLocation getModelResource(EGOS2Lantern animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EGOS2Lantern animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s2_lantern.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EGOS2Lantern animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}