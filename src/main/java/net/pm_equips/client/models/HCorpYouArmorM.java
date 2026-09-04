package net.pm_equips.client.models;

import net.minecraft.resources.ResourceLocation;
import net.pm_equips.items.HCorpYouArmor;
import software.bernie.geckolib.model.GeoModel;

public class HCorpYouArmorM extends GeoModel<HCorpYouArmor> {
    @Override
    public ResourceLocation getModelResource(HCorpYouArmor animatable) {
        return new ResourceLocation("pm_equips", "geo/hcorp_you_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(HCorpYouArmor animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/hcorp_you.png");
    }

    @Override
    public ResourceLocation getAnimationResource(HCorpYouArmor animatable) {
        return new ResourceLocation("pm_equips", "animations/armor.animation.json");
    }
}