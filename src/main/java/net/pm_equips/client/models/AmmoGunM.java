package net.pm_equips.client.models;

import net.minecraft.resources.ResourceLocation;
import net.pm_equips.entity.AmmoGun;
import software.bernie.geckolib.model.GeoModel;

public class AmmoGunM extends GeoModel<AmmoGun> {

    @Override
    public ResourceLocation getModelResource(AmmoGun animatable) {
        return ResourceLocation.parse("pm_equips:geo/p_bullet_gun.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(AmmoGun animatable) {
        return ResourceLocation.parse("pm_equips:textures/entity/p_bullet.png");
    }

    @Override
    public ResourceLocation getAnimationResource(AmmoGun animatable) {
        return ResourceLocation.parse("pm_equips:animations/p_bullet.animation.json");
    }
}
