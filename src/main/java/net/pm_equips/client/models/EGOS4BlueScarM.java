package net.pm_equips.client.models;

import net.pm_equips.items.EGOP4BlueScar;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EGOS4BlueScarM extends GeoModel<EGOP4BlueScar> {
    @Override
    public ResourceLocation getModelResource(EGOP4BlueScar animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor_type1.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EGOP4BlueScar animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s4_blue_scar.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EGOP4BlueScar animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}
