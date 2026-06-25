package net.pm_equips.client.models;

import net.minecraft.resources.ResourceLocation;
import net.pm_equips.items.EGOS3Logging;
import software.bernie.geckolib.model.GeoModel;

public class EGOS3LoggingM extends GeoModel<EGOS3Logging> {
    @Override
    public ResourceLocation getModelResource(EGOS3Logging animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EGOS3Logging animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s3_logging.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EGOS3Logging animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}
