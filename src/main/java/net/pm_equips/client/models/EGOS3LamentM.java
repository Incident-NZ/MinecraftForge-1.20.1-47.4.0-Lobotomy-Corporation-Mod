package net.pm_equips.client.models;

import net.pm_equips.items.EGOP3Lament;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EGOS3LamentM extends GeoModel<EGOP3Lament> {
    @Override
    public ResourceLocation getModelResource(EGOP3Lament animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor_type1.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EGOP3Lament animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s3_lament.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EGOP3Lament animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}
