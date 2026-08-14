package net.pm_equips.client.models;

import net.minecraft.resources.ResourceLocation;
import net.pm_equips.items.EGOP2Bath;
import software.bernie.geckolib.model.GeoModel;

public class EGOS2BathM extends GeoModel<EGOP2Bath> {
    @Override
    public ResourceLocation getModelResource(EGOP2Bath animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor_type2.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EGOP2Bath animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s2_bath.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EGOP2Bath animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}