package net.lobotomy_corporation_mod.client.models;

import net.lobotomy_corporation_mod.items.s4tears;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;

public class s4m_tears extends GeoModel<s4tears> {
    @Override
    public ResourceLocation getModelResource(s4tears animatable) {
        return new ResourceLocation("lobotomy_corporation_mod", "geo/ego_armor.geo.json");
    }

    @Nullable
    @Override
    public ResourceLocation getTextureResource(s4tears animatable) {
        return new ResourceLocation("lobotomy_corporation_mod", "textures/armor/ego_s4_tears.png");
    }

    @Override
    public ResourceLocation getAnimationResource(s4tears animatable) {
        return new ResourceLocation("lobotomy_corporation_mod", "animations/ego_armor.animation.json");
    }
}
