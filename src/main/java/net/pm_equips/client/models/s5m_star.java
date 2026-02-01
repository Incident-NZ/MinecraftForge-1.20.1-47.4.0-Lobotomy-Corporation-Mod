package net.pm_equips.client.models;

import net.pm_equips.items.s5star;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;

public class s5m_star extends GeoModel<s5star> {
    @Override
    public ResourceLocation getModelResource(s5star animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor.geo.json");
    }

    @Nullable
    @Override
    public ResourceLocation getTextureResource(s5star animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s5_star.png");
    }

    @Override
    public ResourceLocation getAnimationResource(s5star animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}
