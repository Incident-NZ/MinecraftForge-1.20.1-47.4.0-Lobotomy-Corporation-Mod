package net.pm_equips.client.models;

import net.pm_equips.items.EGOP5Twilight;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EGOS5TwilightM extends GeoModel<EGOP5Twilight> {
    @Override
    public ResourceLocation getModelResource(EGOP5Twilight animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor_type1.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EGOP5Twilight animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s5_twilight.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EGOP5Twilight animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}
