package net.lobotomy_corporation_mod.client.models;

import net.lobotomy_corporation_mod.items.s5justitia;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;

public class s5m_justitia extends GeoModel<s5justitia> {
    @Override
    public ResourceLocation getModelResource(s5justitia animatable) {
        return new ResourceLocation("lobotomy_corporation_mod", "geo/ego_armor.geo.json");
    }

    @Nullable
    @Override
    public ResourceLocation getTextureResource(s5justitia animatable) {
        return new ResourceLocation("lobotomy_corporation_mod", "textures/armor/ego_s5_justitia.png");
    }

    @Override
    public ResourceLocation getAnimationResource(s5justitia animatable) {
        return new ResourceLocation("lobotomy_corporation_mod", "animations/ego_armor.animation.json");
    }
}
