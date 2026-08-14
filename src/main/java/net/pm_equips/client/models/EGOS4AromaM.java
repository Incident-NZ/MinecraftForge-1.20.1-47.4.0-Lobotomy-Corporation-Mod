package net.pm_equips.client.models;

import net.minecraft.resources.ResourceLocation;
import net.pm_equips.items.EGOP4Aroma;
import software.bernie.geckolib.model.GeoModel;

public class EGOS4AromaM extends GeoModel<EGOP4Aroma> {
    @Override
    public ResourceLocation getModelResource(EGOP4Aroma animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor_type1.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EGOP4Aroma animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s4_aroma.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EGOP4Aroma animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}
