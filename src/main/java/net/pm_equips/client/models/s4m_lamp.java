package net.pm_equips.client.models;

import net.pm_equips.items.s4lamp;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;

public class s4m_lamp extends GeoModel<s4lamp> {
    @Override
    public ResourceLocation getModelResource(s4lamp animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor.geo.json");
    }

    @Nullable
    @Override
    public ResourceLocation getTextureResource(s4lamp animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s4_lamp.png");
    }

    @Override
    public ResourceLocation getAnimationResource(s4lamp animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}
