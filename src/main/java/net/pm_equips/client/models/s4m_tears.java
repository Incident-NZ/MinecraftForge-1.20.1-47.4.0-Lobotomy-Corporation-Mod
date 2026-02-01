package net.pm_equips.client.models;

import net.pm_equips.items.s4tears;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;

public class s4m_tears extends GeoModel<s4tears> {
    @Override
    public ResourceLocation getModelResource(s4tears animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor.geo.json");
    }

    @Nullable
    @Override
    public ResourceLocation getTextureResource(s4tears animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s4_tears.png");
    }

    @Override
    public ResourceLocation getAnimationResource(s4tears animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}
