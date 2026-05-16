package net.pm_equips.client.models;

import net.pm_equips.items.EGOS5Whitenight;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EGOS5WhitenightM extends GeoModel<EGOS5Whitenight> {
    @Override
    public ResourceLocation getModelResource(EGOS5Whitenight animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EGOS5Whitenight animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s5_whitenight.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EGOS5Whitenight animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}
