package net.pm_equips.client.models;

import net.minecraft.resources.ResourceLocation;
import net.pm_equips.entity.AmmoLogicSG;
import software.bernie.geckolib.model.GeoModel;

public class AmmoLogicSGM extends GeoModel<AmmoLogicSG> {

    @Override
    public ResourceLocation getModelResource(AmmoLogicSG animatable) {
        return ResourceLocation.parse("pm_equips:geo/p_bullet.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(AmmoLogicSG animatable) {
        return ResourceLocation.parse("pm_equips:textures/entity/p_bullet_logic.png");
    }

    @Override
    public ResourceLocation getAnimationResource(AmmoLogicSG animatable) {
        return ResourceLocation.parse("pm_equips:animations/p_bullet.animation.json");
    }
}
