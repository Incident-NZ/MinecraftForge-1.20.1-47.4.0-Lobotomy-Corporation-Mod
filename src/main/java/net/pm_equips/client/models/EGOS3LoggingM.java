package net.pm_equips.client.models;

import net.minecraft.resources.ResourceLocation;
import net.pm_equips.items.EGOP3Logging;
import software.bernie.geckolib.model.GeoModel;

public class EGOS3LoggingM extends GeoModel<EGOP3Logging> {
    @Override
    public ResourceLocation getModelResource(EGOP3Logging animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor_type2.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EGOP3Logging animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s3_logging.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EGOP3Logging animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}
