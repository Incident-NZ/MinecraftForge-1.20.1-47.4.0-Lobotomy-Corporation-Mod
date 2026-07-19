package net.pm_equips.client.models;

import net.pm_equips.items.EGOP2Beak;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EGOS2BeakM extends GeoModel<EGOP2Beak> {
    @Override
    public ResourceLocation getModelResource(EGOP2Beak animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor_type2.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EGOP2Beak animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s2_beak.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EGOP2Beak animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}