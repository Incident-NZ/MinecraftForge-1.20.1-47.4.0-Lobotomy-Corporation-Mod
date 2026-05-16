package net.pm_equips.client.models;

import net.pm_equips.items.EGOS3IceShard;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EGOS3IceShardM extends GeoModel<EGOS3IceShard> {
    @Override
    public ResourceLocation getModelResource(EGOS3IceShard animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EGOS3IceShard animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s3_ice_shard.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EGOS3IceShard animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}