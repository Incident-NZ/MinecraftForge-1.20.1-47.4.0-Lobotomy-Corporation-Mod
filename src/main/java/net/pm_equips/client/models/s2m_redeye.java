package net.pm_equips.client.models;

import net.pm_equips.items.s2redeye;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;

public class s2m_redeye extends GeoModel<s2redeye> {
    @Override
    public ResourceLocation getModelResource(s2redeye animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor.geo.json");
    }

    @Nullable
    @Override
    public ResourceLocation getTextureResource(s2redeye animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s2_red_eye.png");
    }

    @Override
    public ResourceLocation getAnimationResource(s2redeye animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}