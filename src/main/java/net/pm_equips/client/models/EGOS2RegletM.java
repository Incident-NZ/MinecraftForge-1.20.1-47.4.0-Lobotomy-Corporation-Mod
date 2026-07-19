package net.pm_equips.client.models;

import net.pm_equips.items.EGOP2Reglet;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EGOS2RegletM extends GeoModel<EGOP2Reglet> {
    @Override
    public ResourceLocation getModelResource(EGOP2Reglet animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor_type1.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EGOP2Reglet animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s2_reglet.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EGOP2Reglet animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}