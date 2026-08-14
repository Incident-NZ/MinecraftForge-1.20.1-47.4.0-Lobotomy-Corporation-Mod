package net.pm_equips.client.models;

import net.minecraft.resources.ResourceLocation;
import net.pm_equips.items.EGOP1Penitence;
import net.pm_equips.items.EGOP1Soda;
import software.bernie.geckolib.model.GeoModel;

public class EGOS1SodaM extends GeoModel<EGOP1Soda> {
    @Override
    public ResourceLocation getModelResource(EGOP1Soda animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor_type2.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EGOP1Soda animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s1_soda.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EGOP1Soda animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}