package net.pm_equips.client.models;

import net.pm_equips.items.EGOS3Lament;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EGOS3LamentM extends GeoModel<EGOS3Lament> {
    @Override
    public ResourceLocation getModelResource(EGOS3Lament animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EGOS3Lament animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s3_lament.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EGOS3Lament animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}
