package net.pm_equips.client.models;

import net.minecraft.resources.ResourceLocation;
import net.pm_equips.items.EGOP3Blood;
import software.bernie.geckolib.model.GeoModel;

public class EGOS3BloodM extends GeoModel<EGOP3Blood> {
    @Override
    public ResourceLocation getModelResource(EGOP3Blood animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor_type1.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EGOP3Blood animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s3_blood.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EGOP3Blood animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}
