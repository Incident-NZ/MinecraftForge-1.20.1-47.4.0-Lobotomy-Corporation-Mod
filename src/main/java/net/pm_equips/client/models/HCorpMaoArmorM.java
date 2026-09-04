package net.pm_equips.client.models;

import net.minecraft.resources.ResourceLocation;
import net.pm_equips.items.HCorpMaoArmor;
import software.bernie.geckolib.model.GeoModel;

public class HCorpMaoArmorM extends GeoModel<HCorpMaoArmor> {
    @Override
    public ResourceLocation getModelResource(HCorpMaoArmor animatable) {
        return new ResourceLocation("pm_equips", "geo/hcorp_mao_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(HCorpMaoArmor animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/hcorp_mao.png");
    }

    @Override
    public ResourceLocation getAnimationResource(HCorpMaoArmor animatable) {
        return new ResourceLocation("pm_equips", "animations/armor.animation.json");
    }
}