package net.lobotomy_corporation_mod.client.models;

import net.lobotomy_corporation_mod.items.s3lament;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;

public class s3m_lament extends GeoModel<s3lament> {
    @Override
    public ResourceLocation getModelResource(s3lament animatable) {
        return new ResourceLocation("lobotomy_corporation_mod", "geo/ego_armor.geo.json");
    }

    @Nullable
    @Override
    public ResourceLocation getTextureResource(s3lament animatable) {
        return new ResourceLocation("lobotomy_corporation_mod", "textures/armor/ego_s3_lament.png");
    }

    @Override
    public ResourceLocation getAnimationResource(s3lament animatable) {
        return new ResourceLocation("lobotomy_corporation_mod", "animation/ego_armor.animation.json");
    }
}
