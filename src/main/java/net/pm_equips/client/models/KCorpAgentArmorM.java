package net.pm_equips.client.models;

import net.minecraft.resources.ResourceLocation;
import net.pm_equips.items.KCorpAgentArmor;
import software.bernie.geckolib.model.GeoModel;

public class KCorpAgentArmorM extends GeoModel<KCorpAgentArmor> {

    @Override
    public ResourceLocation getModelResource(KCorpAgentArmor animatable) {
        return new ResourceLocation("pm_equips", "geo/kcorp_agent_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(KCorpAgentArmor animatable) {
        return new ResourceLocation("pm_equips", "textures/armor/kcorp_agent_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(KCorpAgentArmor animatable) {
        return new ResourceLocation("pm_equips", "animations/armor.animation.json");
    }
}
