package net.pm_equips.client.models;

import net.pm_equips.items.s3iceshard;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;

public class s3m_iceshard extends GeoModel<s3iceshard> {
    @Override
    public ResourceLocation getModelResource(s3iceshard animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor.geo.json");
    }

    @Nullable
    @Override
    public ResourceLocation getTextureResource(s3iceshard animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s3_ice_shard.png");
    }

    @Override
    public ResourceLocation getAnimationResource(s3iceshard animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}