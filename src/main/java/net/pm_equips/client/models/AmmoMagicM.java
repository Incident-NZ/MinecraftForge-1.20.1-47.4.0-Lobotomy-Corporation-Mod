package net.pm_equips.client.models;

import net.minecraft.resources.ResourceLocation;
import net.pm_equips.entity.EGOMagicP;
import software.bernie.geckolib.model.GeoModel;

public class AmmoMagicM extends GeoModel<EGOMagicP> {

    @Override
    public ResourceLocation getModelResource(EGOMagicP animatable) {
        return ResourceLocation.parse("pm_equips:geo/p_bullet_gun.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EGOMagicP animatable) {
        return ResourceLocation.parse("pm_equips:textures/entity/p_bullet_magic.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EGOMagicP animatable) {
        return ResourceLocation.parse("pm_equips:animations/p_bullet.animation.json");
    }
}
