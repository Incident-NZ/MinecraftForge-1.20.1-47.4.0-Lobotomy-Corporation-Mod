package net.pm_equips.client.models;

import net.pm_equips.items.EGOP1Penitence;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EGOS1PenitenceM extends GeoModel<EGOP1Penitence> {
    @Override
    public ResourceLocation getModelResource(EGOP1Penitence animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor_type2.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EGOP1Penitence animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s1_penitence.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EGOP1Penitence animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}