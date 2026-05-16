package net.pm_equips.client.models;

import net.pm_equips.items.EGOS5Twilight;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EGOS5TwilightM extends GeoModel<EGOS5Twilight> {
    @Override
    public ResourceLocation getModelResource(EGOS5Twilight animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EGOS5Twilight animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s5_twilight.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EGOS5Twilight animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}
