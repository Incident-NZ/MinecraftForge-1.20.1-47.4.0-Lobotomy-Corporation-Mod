package net.pm_equips.client.models;

import net.pm_equips.items.EGOS2Beak;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EGOS2BeakM extends GeoModel<EGOS2Beak> {
    @Override
    public ResourceLocation getModelResource(EGOS2Beak animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EGOS2Beak animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s2_beak.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EGOS2Beak animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}