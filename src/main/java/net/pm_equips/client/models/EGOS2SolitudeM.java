package net.pm_equips.client.models;

import net.minecraft.resources.ResourceLocation;
import net.pm_equips.items.EGOP2Solitude;
import software.bernie.geckolib.model.GeoModel;

public class EGOS2SolitudeM extends GeoModel<EGOP2Solitude> {
    @Override
        public ResourceLocation getModelResource(EGOP2Solitude animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor_type2.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EGOP2Solitude animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s2_solitude.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EGOP2Solitude animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}