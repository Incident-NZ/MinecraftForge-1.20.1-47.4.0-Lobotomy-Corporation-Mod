package net.pm_equips.client.models;

import net.pm_equips.items.EGOS5Justitia;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EGOS5JustitiaM extends GeoModel<EGOS5Justitia> {
    @Override
    public ResourceLocation getModelResource(EGOS5Justitia animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EGOS5Justitia animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s5_justitia.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EGOS5Justitia animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}
