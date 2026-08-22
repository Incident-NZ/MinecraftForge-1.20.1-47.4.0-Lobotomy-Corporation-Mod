package net.pm_equips.client.models;

import net.minecraft.resources.ResourceLocation;
import net.pm_equips.items.KCorpOfficerArmor;
import software.bernie.geckolib.model.GeoModel;

public class KCorpOfficerArmorM extends GeoModel<KCorpOfficerArmor> {

    @Override
    public ResourceLocation getModelResource(KCorpOfficerArmor animatable) {
        return new ResourceLocation("pm_equips", "geo/kcorp_officer_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(KCorpOfficerArmor animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/kcorp_officer_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(KCorpOfficerArmor animatable) {
        return new ResourceLocation("pm_equips", "animations/armor.animation.json");
    }
}
