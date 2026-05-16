package net.pm_equips.client.models;

import net.pm_equips.items.EGOS5Mimicry;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EGOS5MimicryM extends GeoModel<EGOS5Mimicry> {
    @Override
    public ResourceLocation getModelResource(EGOS5Mimicry animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EGOS5Mimicry animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s5_mimicry.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EGOS5Mimicry animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}
