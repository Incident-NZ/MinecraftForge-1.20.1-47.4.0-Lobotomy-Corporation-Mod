package net.pm_equips.client.models;

import net.pm_equips.items.EGOS3Laetitia;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EGOS3LaetitiaM extends GeoModel<EGOS3Laetitia> {
    @Override
    public ResourceLocation getModelResource(EGOS3Laetitia animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EGOS3Laetitia animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s3_laetitia.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EGOS3Laetitia animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}