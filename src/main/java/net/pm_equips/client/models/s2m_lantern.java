package net.pm_equips.client.models;

import net.pm_equips.items.s2lantern;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;

public class s2m_lantern extends GeoModel<s2lantern> {
    @Override
    public ResourceLocation getModelResource(s2lantern animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor.geo.json");
    }

    @Nullable
    @Override
    public ResourceLocation getTextureResource(s2lantern animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s2_lantern.png");
    }

    @Override
    public ResourceLocation getAnimationResource(s2lantern animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}