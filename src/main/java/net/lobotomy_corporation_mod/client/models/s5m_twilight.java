package net.lobotomy_corporation_mod.client.models;

import net.lobotomy_corporation_mod.items.s5twilight;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;

public class s5m_twilight extends GeoModel<s5twilight> {
    @Override
    public ResourceLocation getModelResource(s5twilight animatable) {
        return new ResourceLocation("lobotomy_corporation_mod", "geo/ego_armor.geo.json");
    }

    @Nullable
    @Override
    public ResourceLocation getTextureResource(s5twilight animatable) {
        return new ResourceLocation("lobotomy_corporation_mod", "textures/armor/ego_s5_twilight.png");
    }

    @Override
    public ResourceLocation getAnimationResource(s5twilight animatable) {
        return new ResourceLocation("lobotomy_corporation_mod", "animations/ego_armor.animation.json");
    }
}
