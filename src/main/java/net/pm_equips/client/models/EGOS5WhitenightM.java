package net.pm_equips.client.models;

import net.pm_equips.items.EGOP5WhiteNight;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EGOS5WhitenightM extends GeoModel<EGOP5WhiteNight> {
    @Override
    public ResourceLocation getModelResource(EGOP5WhiteNight animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor_type2.geo.json");
    }

    @Override
        public ResourceLocation getTextureResource(EGOP5WhiteNight animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s5_whitenight.png");
    }

    @Override
        public ResourceLocation getAnimationResource(EGOP5WhiteNight animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}
