package net.lobotomy_corporation_mod.client.models;

import net.lobotomy_corporation_mod.items.s2beak;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;

public class s2m_beak extends GeoModel<s2beak> {
    @Override
    public ResourceLocation getModelResource(s2beak animatable) {
        return new ResourceLocation("lobotomy_corporation_mod", "geo/ego_armor.geo.json");
    }

    @Nullable
    @Override
    public ResourceLocation getTextureResource(s2beak animatable) {
        return new ResourceLocation("lobotomy_corporation_mod", "textures/armor/ego_s2_beak.png");
    }

    @Override
    public ResourceLocation getAnimationResource(s2beak animatable) {
        return new ResourceLocation("lobotomy_corporation_mod", "animations/ego_armor.animation.json");
    }
}