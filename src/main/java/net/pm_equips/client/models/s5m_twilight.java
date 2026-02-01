package net.pm_equips.client.models;

import net.pm_equips.items.s5twilight;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;

public class s5m_twilight extends GeoModel<s5twilight> {
    @Override
    public ResourceLocation getModelResource(s5twilight animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor.geo.json");
    }

    @Nullable
    @Override
    public ResourceLocation getTextureResource(s5twilight animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s5_twilight.png");
    }

    @Override
    public ResourceLocation getAnimationResource(s5twilight animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}
