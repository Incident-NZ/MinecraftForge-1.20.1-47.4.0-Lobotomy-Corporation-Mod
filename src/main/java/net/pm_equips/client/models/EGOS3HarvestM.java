package net.pm_equips.client.models;

import net.minecraft.resources.ResourceLocation;
import net.pm_equips.items.EGOS3Harvest;
import software.bernie.geckolib.model.GeoModel;

public class EGOS3HarvestM extends GeoModel<EGOS3Harvest> {
    @Override
    public ResourceLocation getModelResource(EGOS3Harvest animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EGOS3Harvest animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s3_harvest.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EGOS3Harvest animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}
