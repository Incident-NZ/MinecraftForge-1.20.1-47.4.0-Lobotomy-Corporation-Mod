package net.pm_equips.client.models;

import net.pm_equips.items.EGOP3Laetitia;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EGOS3LaetitiaM extends GeoModel<EGOP3Laetitia> {
    @Override
    public ResourceLocation getModelResource(EGOP3Laetitia animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor_type1.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EGOP3Laetitia animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s3_laetitia.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EGOP3Laetitia animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}