package net.pm_equips.client.models;

import net.minecraft.resources.ResourceLocation;
import net.pm_equips.items.CorePageItem;
import software.bernie.geckolib.model.GeoModel;

public class CorePageModel extends GeoModel<CorePageItem> {
    @Override
    public ResourceLocation getModelResource(CorePageItem item) {
        return item.getModelResource();
    }

    @Override
    public ResourceLocation getTextureResource(CorePageItem item) {
        return item.getTextureResource();
    }

    @Override
    public ResourceLocation getAnimationResource(CorePageItem item) {
        return item.getAnimationResource();
    }
}
