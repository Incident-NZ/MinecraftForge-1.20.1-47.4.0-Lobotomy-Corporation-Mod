package net.pm_equips.client.models;

import net.pm_equips.items.EGOS5Star;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EGOS5StarM extends GeoModel<EGOS5Star> {
    @Override
    public ResourceLocation getModelResource(EGOS5Star animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EGOS5Star animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s5_star.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EGOS5Star animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}
