package net.pm_equips.client.models;

import net.minecraft.resources.ResourceLocation;
import net.pm_equips.items.WCorpArmor;
import software.bernie.geckolib.model.GeoModel;

public class WCorpArmorM extends GeoModel<WCorpArmor> {

    @Override
    public ResourceLocation getModelResource(WCorpArmor animatable) {
        return new ResourceLocation("pm_equips", "geo/wcorp_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(WCorpArmor animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/wcorp_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(WCorpArmor animatable) {
        return new ResourceLocation("pm_equips", "animations/armor.animation.json");
    }
}
