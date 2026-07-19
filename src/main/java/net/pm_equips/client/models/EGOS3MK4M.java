package net.pm_equips.client.models;

import net.minecraft.resources.ResourceLocation;
import net.pm_equips.items.EGOP3MK4;
import software.bernie.geckolib.model.GeoModel;

public class EGOS3MK4M extends GeoModel<EGOP3MK4> {
    @Override
    public ResourceLocation getModelResource(EGOP3MK4 animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor_type1.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EGOP3MK4 animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s3_mk4.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EGOP3MK4 animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}
