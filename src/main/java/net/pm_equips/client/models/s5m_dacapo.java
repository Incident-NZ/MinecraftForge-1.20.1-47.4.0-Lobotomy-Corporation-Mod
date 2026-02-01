package net.pm_equips.client.models;

import net.pm_equips.items.s5dacapo;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;

public class s5m_dacapo extends GeoModel<s5dacapo> {
    @Override
    public ResourceLocation getModelResource(s5dacapo animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor.geo.json");
    }

    @Nullable
    @Override
    public ResourceLocation getTextureResource(s5dacapo animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s5_da_capo.png");
    }

    @Override
    public ResourceLocation getAnimationResource(s5dacapo animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}
