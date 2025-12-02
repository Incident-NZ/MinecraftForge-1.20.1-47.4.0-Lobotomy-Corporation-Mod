package net.lobotomy_corporation_mod.client.models;

import net.lobotomy_corporation_mod.items.s1penitence;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;

public class s1m_penitence extends GeoModel<s1penitence> {
    @Override
    public ResourceLocation getModelResource(s1penitence animatable) {
        return new ResourceLocation("lobotomy_corporation_mod", "geo/ego_armor.geo.json");
    }

    @Nullable
    @Override
    public ResourceLocation getTextureResource(s1penitence animatable) {
        return new ResourceLocation("lobotomy_corporation_mod", "textures/armor/ego_s1_penitence.png");
    }

    @Override
    public ResourceLocation getAnimationResource(s1penitence animatable) {
        return new ResourceLocation("lobotomy_corporation_mod", "animations/ego_armor.animation.json");
    }
}