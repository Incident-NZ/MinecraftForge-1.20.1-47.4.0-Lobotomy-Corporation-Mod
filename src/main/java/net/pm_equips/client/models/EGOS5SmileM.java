package net.pm_equips.client.models;

import net.pm_equips.items.EGOS5Smile;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EGOS5SmileM extends GeoModel<EGOS5Smile> {
    @Override
    public ResourceLocation getModelResource(EGOS5Smile animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EGOS5Smile animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s5_smile.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EGOS5Smile animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}
