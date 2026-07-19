package net.pm_equips.client.models;

import net.pm_equips.items.EGOP2Match;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EGOS2MatchM extends GeoModel<EGOP2Match> {
    @Override
    public ResourceLocation getModelResource(EGOP2Match animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor_type1.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EGOP2Match animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s2_match.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EGOP2Match animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}