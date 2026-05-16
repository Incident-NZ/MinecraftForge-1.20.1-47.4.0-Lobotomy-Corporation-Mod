package net.pm_equips.client.models;

import net.pm_equips.items.EGOS3MagicBullet;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EGOS3MagicBulletM extends GeoModel<EGOS3MagicBullet> {
    @Override
    public ResourceLocation getModelResource(EGOS3MagicBullet animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EGOS3MagicBullet animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s3_magic_bullet.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EGOS3MagicBullet animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}
