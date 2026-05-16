package net.pm_equips.client.models;

import net.minecraft.resources.ResourceLocation;
import net.pm_equips.items.WCorpArmorAce;
import software.bernie.geckolib.model.GeoModel;

public class WCorpArmorAceM extends GeoModel<WCorpArmorAce> {

    @Override
    public ResourceLocation getModelResource(WCorpArmorAce animatable) {
        return new ResourceLocation("pm_equips", "geo/wcorp_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(WCorpArmorAce animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/wcorp_armor_adept.png");
    }

    @Override
    public ResourceLocation getAnimationResource(WCorpArmorAce animatable) {
        return new ResourceLocation("pm_equips", "animations/wcorp.animation.json");
    }
}
