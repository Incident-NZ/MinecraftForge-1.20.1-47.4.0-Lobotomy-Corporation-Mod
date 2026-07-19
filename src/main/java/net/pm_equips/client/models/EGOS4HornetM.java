package net.pm_equips.client.models;

import net.pm_equips.items.EGOP4Hornet;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EGOS4HornetM extends GeoModel<EGOP4Hornet> {
    @Override
    public ResourceLocation getModelResource(EGOP4Hornet animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor_type1.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EGOP4Hornet animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s4_hornet.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EGOP4Hornet animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}
