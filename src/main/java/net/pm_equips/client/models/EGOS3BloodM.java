package net.pm_equips.client.models;

import net.minecraft.resources.ResourceLocation;
import net.pm_equips.items.EGOS3Blood;
import software.bernie.geckolib.model.GeoModel;

public class EGOS3BloodM extends GeoModel<EGOS3Blood> {
    @Override
    public ResourceLocation getModelResource(EGOS3Blood animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EGOS3Blood animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s3_blood.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EGOS3Blood animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}
