package net.pm_equips.client.models;

import net.pm_equips.items.EGOP3MagicBullet;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EGOS3MagicBulletM extends GeoModel<EGOP3MagicBullet> {
    @Override
    public ResourceLocation getModelResource(EGOP3MagicBullet animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor_type1.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EGOP3MagicBullet animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s3_magic_bullet.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EGOP3MagicBullet animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}
