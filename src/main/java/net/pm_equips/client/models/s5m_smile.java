package net.pm_equips.client.models;

import net.pm_equips.items.s5smile;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;

public class s5m_smile extends GeoModel<s5smile> {
    @Override
    public ResourceLocation getModelResource(s5smile animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor.geo.json");
    }

    @Nullable
    @Override
    public ResourceLocation getTextureResource(s5smile animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s5_smile.png");
    }

    @Override
    public ResourceLocation getAnimationResource(s5smile animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}
