package net.pm_equips.client.models;

import net.minecraft.resources.ResourceLocation;
import net.pm_equips.items.RCorp4thRabbitArmor;
import software.bernie.geckolib.model.GeoModel;

public class RCorp4thRabbitArmorM extends GeoModel<RCorp4thRabbitArmor> {

    @Override
    public ResourceLocation getModelResource(RCorp4thRabbitArmor animatable) {
        return new ResourceLocation("pm_equips", "geo/rcorp_4th_rabbit_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(RCorp4thRabbitArmor animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/rcorp_4th_rabbit_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(RCorp4thRabbitArmor animatable) {
        return new ResourceLocation("pm_equips", "animations/armor.animation.json");
    }
}
