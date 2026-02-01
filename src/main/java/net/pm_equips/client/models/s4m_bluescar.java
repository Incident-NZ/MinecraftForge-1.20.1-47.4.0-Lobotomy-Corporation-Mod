package net.pm_equips.client.models;

import net.pm_equips.items.s4bluescar;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;

public class s4m_bluescar extends GeoModel<s4bluescar> {
    @Override
    public ResourceLocation getModelResource(s4bluescar animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor.geo.json");
    }

    @Nullable
    @Override
    public ResourceLocation getTextureResource(s4bluescar animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s4_blue_scar.png");
    }

    @Override
    public ResourceLocation getAnimationResource(s4bluescar animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}
