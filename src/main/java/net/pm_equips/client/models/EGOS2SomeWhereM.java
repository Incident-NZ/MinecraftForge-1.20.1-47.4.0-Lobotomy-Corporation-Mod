package net.pm_equips.client.models;

import net.minecraft.resources.ResourceLocation;
import net.pm_equips.items.EGOP2SomeWhere;
import software.bernie.geckolib.model.GeoModel;

public class EGOS2SomeWhereM extends GeoModel<EGOP2SomeWhere> {
    @Override
    public ResourceLocation getModelResource(EGOP2SomeWhere animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor_type1.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EGOP2SomeWhere animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s2_somewhere.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EGOP2SomeWhere animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}