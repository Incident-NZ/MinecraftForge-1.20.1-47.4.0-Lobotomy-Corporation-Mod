package net.pm_equips.client.models;

import net.pm_equips.items.EGOS2Reglet;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EGOS2RegletM extends GeoModel<EGOS2Reglet> {
    @Override
    public ResourceLocation getModelResource(EGOS2Reglet animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EGOS2Reglet animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s2_reglet.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EGOS2Reglet animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}