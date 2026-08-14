package net.pm_equips.client.models;

import net.minecraft.resources.ResourceLocation;
import net.pm_equips.items.EGOP4Hatred;
import software.bernie.geckolib.model.GeoModel;

public class EGOS4HatredM extends GeoModel<EGOP4Hatred> {
    @Override
    public ResourceLocation getModelResource(EGOP4Hatred animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor_type2.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EGOP4Hatred animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s4_hatred.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EGOP4Hatred animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}
