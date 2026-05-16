package net.pm_equips.client.models;

import net.pm_equips.items.EGOS2Match;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EGOS2MatchM extends GeoModel<EGOS2Match> {
    @Override
    public ResourceLocation getModelResource(EGOS2Match animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EGOS2Match animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s2_match.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EGOS2Match animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}