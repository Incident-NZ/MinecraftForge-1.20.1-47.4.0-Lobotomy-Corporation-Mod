package net.pm_equips.client.models;

import net.pm_equips.items.EGOS1Penitence;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;

public class EGOS1PenitenceM extends GeoModel<EGOS1Penitence> {
    @Override
    public ResourceLocation getModelResource(EGOS1Penitence animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor.geo.json");
    }

    @Nullable
    @Override
    public ResourceLocation getTextureResource(EGOS1Penitence animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s1_penitence.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EGOS1Penitence animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}