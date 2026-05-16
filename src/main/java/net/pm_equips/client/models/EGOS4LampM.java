package net.pm_equips.client.models;

import net.pm_equips.items.EGOS4Lamp;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EGOS4LampM extends GeoModel<EGOS4Lamp> {
    @Override
    public ResourceLocation getModelResource(EGOS4Lamp animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EGOS4Lamp animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s4_lamp.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EGOS4Lamp animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}
