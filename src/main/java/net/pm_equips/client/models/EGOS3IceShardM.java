package net.pm_equips.client.models;

import net.pm_equips.items.EGOP3IceShard;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EGOS3IceShardM extends GeoModel<EGOP3IceShard> {
    @Override
    public ResourceLocation getModelResource(EGOP3IceShard animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor_type1.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EGOP3IceShard animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s3_ice_shard.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EGOP3IceShard animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}