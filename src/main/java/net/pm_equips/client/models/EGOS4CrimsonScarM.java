package net.pm_equips.client.models;

import net.minecraft.resources.ResourceLocation;
import net.pm_equips.items.EGOP4CrimsonScar;
import software.bernie.geckolib.model.GeoModel;

public class EGOS4CrimsonScarM extends GeoModel<EGOP4CrimsonScar> {
    @Override
    public ResourceLocation getModelResource(EGOP4CrimsonScar animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor_type1.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EGOP4CrimsonScar animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s4_crimson_scar.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EGOP4CrimsonScar animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}
