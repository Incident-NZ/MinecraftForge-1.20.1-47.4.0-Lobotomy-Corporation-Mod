package net.pm_equips.client.models;

import net.pm_equips.items.s4hornet;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;

public class s4m_hornet extends GeoModel<s4hornet> {
    @Override
    public ResourceLocation getModelResource(s4hornet animatable) {
        return new ResourceLocation("pm_equips", "geo/ego_armor.geo.json");
    }

    @Nullable
    @Override
    public ResourceLocation getTextureResource(s4hornet animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/ego_s4_hornet.png");
    }

    @Override
    public ResourceLocation getAnimationResource(s4hornet animatable) {
        return new ResourceLocation("pm_equips", "animations/ego_armor.animation.json");
    }
}
