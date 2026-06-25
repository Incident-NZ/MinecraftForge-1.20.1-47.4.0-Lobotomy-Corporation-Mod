package net.pm_equips.client.models;

import net.minecraft.resources.ResourceLocation;
import net.pm_equips.items.EGOS3Galaxy;
import software.bernie.geckolib.model.GeoModel;

public class EGOS3GalaxyM extends GeoModel<EGOS3Galaxy> {
    @Override
    public ResourceLocation getModelResource(EGOS3Galaxy animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EGOS3Galaxy animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s3_galaxy.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EGOS3Galaxy animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}
