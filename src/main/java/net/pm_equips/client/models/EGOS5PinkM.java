package net.pm_equips.client.models;

import net.minecraft.resources.ResourceLocation;
import net.pm_equips.items.EGOP5Pink;
import software.bernie.geckolib.model.GeoModel;

public class EGOS5PinkM extends GeoModel<EGOP5Pink> {
    @Override
    public ResourceLocation getModelResource(EGOP5Pink animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor_type2.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EGOP5Pink animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s5_pink.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EGOP5Pink animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}
