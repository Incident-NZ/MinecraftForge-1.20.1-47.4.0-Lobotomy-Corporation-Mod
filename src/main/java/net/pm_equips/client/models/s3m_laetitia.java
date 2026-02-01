package net.pm_equips.client.models;

import net.pm_equips.items.s3laetitia;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;

public class s3m_laetitia extends GeoModel<s3laetitia> {
    @Override
    public ResourceLocation getModelResource(s3laetitia animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor.geo.json");
    }

    @Nullable
    @Override
    public ResourceLocation getTextureResource(s3laetitia animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s3_laetitia.png");
    }

    @Override
    public ResourceLocation getAnimationResource(s3laetitia animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}