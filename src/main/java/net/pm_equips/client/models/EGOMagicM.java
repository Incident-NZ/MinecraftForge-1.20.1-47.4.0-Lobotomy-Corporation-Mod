package net.pm_equips.client.models;

import net.minecraft.resources.ResourceLocation;
import net.pm_equips.entity.EGOHatredMagicP;
import software.bernie.geckolib.model.GeoModel;

public class EGOMagicM extends GeoModel<EGOHatredMagicP> {

    @Override
    public ResourceLocation getModelResource(EGOHatredMagicP animatable) {
        return ResourceLocation.parse("pm_equips:geo/p_magic.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EGOHatredMagicP animatable) {
        return ResourceLocation.parse("pm_equips:textures/entity/p_magic.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EGOHatredMagicP animatable) {
        return ResourceLocation.parse("pm_equips:animations/p_bullet.animation.json");
    }
}
