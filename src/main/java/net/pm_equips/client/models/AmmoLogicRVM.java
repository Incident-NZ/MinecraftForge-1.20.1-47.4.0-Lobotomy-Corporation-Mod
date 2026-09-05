package net.pm_equips.client.models;

import net.minecraft.resources.ResourceLocation;
import net.pm_equips.entity.AmmoLogicRV;
import software.bernie.geckolib.model.GeoModel;

public class AmmoLogicRVM extends GeoModel<AmmoLogicRV> {

    @Override
    public ResourceLocation getModelResource(AmmoLogicRV animatable) {
        return ResourceLocation.parse("pm_equips:geo/p_bullet_gun.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(AmmoLogicRV animatable) {
        return ResourceLocation.parse("pm_equips:textures/entity/p_bullet_logic.png");
    }

    @Override
    public ResourceLocation getAnimationResource(AmmoLogicRV animatable) {
        return ResourceLocation.parse("pm_equips:animations/p_bullet.animation.json");
    }
}
