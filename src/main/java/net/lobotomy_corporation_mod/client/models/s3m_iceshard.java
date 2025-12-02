package net.lobotomy_corporation_mod.client.models;

import net.lobotomy_corporation_mod.items.s3iceshard;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;

public class s3m_iceshard extends GeoModel<s3iceshard> {
    @Override
    public ResourceLocation getModelResource(s3iceshard animatable) {
        return new ResourceLocation("lobotomy_corporation_mod", "geo/ego_armor.geo.json");
    }

    @Nullable
    @Override
    public ResourceLocation getTextureResource(s3iceshard animatable) {
        return new ResourceLocation("lobotomy_corporation_mod", "textures/armor/ego_s3_ice_shard.png");
    }

    @Override
    public ResourceLocation getAnimationResource(s3iceshard animatable) {
        return new ResourceLocation("lobotomy_corporation_mod", "animations/ego_armor.animation.json");
    }
}