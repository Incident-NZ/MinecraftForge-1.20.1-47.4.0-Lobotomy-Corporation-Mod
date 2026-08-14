package net.pm_equips.client.models;

import net.pm_equips.items.EGOP4Heaven;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EGOS5DaCapoM extends GeoModel<EGOP4Heaven> {
    @Override
    public ResourceLocation getModelResource(EGOP4Heaven animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor_type2.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EGOP4Heaven animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s5_da_capo.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EGOP4Heaven animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}
