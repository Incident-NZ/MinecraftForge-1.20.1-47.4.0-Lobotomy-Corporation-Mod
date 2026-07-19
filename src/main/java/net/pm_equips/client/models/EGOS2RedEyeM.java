package net.pm_equips.client.models;

import net.pm_equips.items.EGOP2RedEye;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EGOS2RedEyeM extends GeoModel<EGOP2RedEye> {
    @Override
    public ResourceLocation getModelResource(EGOP2RedEye animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor_type1.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EGOP2RedEye animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s2_red_eye.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EGOP2RedEye animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}