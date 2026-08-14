package net.pm_equips.client.models;

import net.minecraft.resources.ResourceLocation;
import net.pm_equips.items.EGOP3Bear;
import software.bernie.geckolib.model.GeoModel;

public class EGOS3BearM extends GeoModel<EGOP3Bear> {
    @Override
    public ResourceLocation getModelResource(EGOP3Bear animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor_type2.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EGOP3Bear animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s3_bear.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EGOP3Bear animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}
