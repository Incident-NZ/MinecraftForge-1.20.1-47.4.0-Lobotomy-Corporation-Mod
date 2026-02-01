package net.pm_equips.client.models;

import net.pm_equips.items.s3lament;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;

public class s3m_lament extends GeoModel<s3lament> {
    @Override
    public ResourceLocation getModelResource(s3lament animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor.geo.json");
    }

    @Nullable
    @Override
    public ResourceLocation getTextureResource(s3lament animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s3_lament.png");
    }

    @Override
    public ResourceLocation getAnimationResource(s3lament animatable) {
        return new ResourceLocation("pm_equips", "animation/ego_armor.animation.json");
    }
}
