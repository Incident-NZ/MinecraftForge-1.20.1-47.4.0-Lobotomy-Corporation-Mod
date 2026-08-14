package net.pm_equips.client.models;

import net.minecraft.resources.ResourceLocation;
import net.pm_equips.items.EGOP4Heaven;
import software.bernie.geckolib.model.GeoModel;

public class EGOS4HeavenM extends GeoModel<EGOP4Heaven> {
    @Override
    public ResourceLocation getModelResource(EGOP4Heaven animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor_type2.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EGOP4Heaven animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s4_heaven.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EGOP4Heaven animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}
