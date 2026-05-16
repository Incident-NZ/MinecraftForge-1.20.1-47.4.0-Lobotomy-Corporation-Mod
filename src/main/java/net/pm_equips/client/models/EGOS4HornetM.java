package net.pm_equips.client.models;

import net.pm_equips.items.EGOS4Hornet;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EGOS4HornetM extends GeoModel<EGOS4Hornet> {
    @Override
    public ResourceLocation getModelResource(EGOS4Hornet animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EGOS4Hornet animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s4_hornet.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EGOS4Hornet animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}
