package net.pm_equips.client.models;

import net.pm_equips.items.EGOS4Tears;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EGOS4TearsM extends GeoModel<EGOS4Tears> {
    @Override
    public ResourceLocation getModelResource(EGOS4Tears animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EGOS4Tears animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s4_tears.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EGOS4Tears animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}
