package net.lobotomy_corporation_mod.client.models;

import net.lobotomy_corporation_mod.items.s2reglet;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;

public class s2m_reglet extends GeoModel<s2reglet> {
    @Override
    public ResourceLocation getModelResource(s2reglet animatable) {
        return new ResourceLocation("lobotomy_corporation_mod", "geo/ego_armor.geo.json");
    }

    @Nullable
    @Override
    public ResourceLocation getTextureResource(s2reglet animatable) {
        return new ResourceLocation("lobotomy_corporation_mod", "textures/armor/ego_s2_reglet.png");
    }

    @Override
    public ResourceLocation getAnimationResource(s2reglet animatable) {
        return new ResourceLocation("lobotomy_corporation_mod", "animations/ego_armor.animation.json");
    }
}