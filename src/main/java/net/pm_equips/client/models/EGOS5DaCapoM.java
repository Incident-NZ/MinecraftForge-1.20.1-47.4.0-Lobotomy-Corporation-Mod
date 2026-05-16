package net.pm_equips.client.models;

import net.pm_equips.items.EGOS5DaCapo;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EGOS5DaCapoM extends GeoModel<EGOS5DaCapo> {
    @Override
    public ResourceLocation getModelResource(EGOS5DaCapo animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EGOS5DaCapo animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s5_da_capo.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EGOS5DaCapo animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}
