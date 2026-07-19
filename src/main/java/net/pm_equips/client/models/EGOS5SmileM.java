package net.pm_equips.client.models;

import net.pm_equips.items.EGOP5Smile;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EGOS5SmileM extends GeoModel<EGOP5Smile> {
    @Override
    public ResourceLocation getModelResource(EGOP5Smile animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor_type2.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EGOP5Smile animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s5_smile.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EGOP5Smile animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}
