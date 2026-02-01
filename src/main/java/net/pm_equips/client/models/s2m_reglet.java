package net.pm_equips.client.models;

import net.pm_equips.items.s2reglet;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;

public class s2m_reglet extends GeoModel<s2reglet> {
    @Override
    public ResourceLocation getModelResource(s2reglet animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor.geo.json");
    }

    @Nullable
    @Override
    public ResourceLocation getTextureResource(s2reglet animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s2_reglet.png");
    }

    @Override
    public ResourceLocation getAnimationResource(s2reglet animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}