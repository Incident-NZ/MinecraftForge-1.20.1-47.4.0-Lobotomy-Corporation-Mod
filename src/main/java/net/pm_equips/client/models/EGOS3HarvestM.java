package net.pm_equips.client.models;

import net.minecraft.resources.ResourceLocation;
import net.pm_equips.items.EGOP3Harvest;
import software.bernie.geckolib.model.GeoModel;

public class EGOS3HarvestM extends GeoModel<EGOP3Harvest> {
    @Override
    public ResourceLocation getModelResource(EGOP3Harvest animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor_type2.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EGOP3Harvest animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s3_harvest.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EGOP3Harvest animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}
