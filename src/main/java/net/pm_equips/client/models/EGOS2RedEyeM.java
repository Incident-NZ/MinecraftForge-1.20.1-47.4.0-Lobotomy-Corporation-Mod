package net.pm_equips.client.models;

import net.pm_equips.items.EGOS2RedEye;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EGOS2RedEyeM extends GeoModel<EGOS2RedEye> {
    @Override
    public ResourceLocation getModelResource(EGOS2RedEye animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EGOS2RedEye animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s2_red_eye.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EGOS2RedEye animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}