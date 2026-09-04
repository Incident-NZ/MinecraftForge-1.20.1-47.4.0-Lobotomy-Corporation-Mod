package net.pm_equips.client.models;

import net.minecraft.resources.ResourceLocation;
import net.pm_equips.entity.AmmoExp;
import software.bernie.geckolib.model.GeoModel;

public class AmmoExpM extends GeoModel<AmmoExp> {

    @Override
    public ResourceLocation getModelResource(AmmoExp animatable) {
        return ResourceLocation.parse("pm_equips:geo/p_bullet_gun.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(AmmoExp animatable) {
        return ResourceLocation.parse("pm_equips:textures/entity/p_bullet.png");
    }

    @Override
    public ResourceLocation getAnimationResource(AmmoExp animatable) {
        return ResourceLocation.parse("pm_equips:animations/p_bullet.animation.json");
    }
}
