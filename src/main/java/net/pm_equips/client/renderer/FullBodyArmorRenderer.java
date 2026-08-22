package net.pm_equips.client.renderer;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class FullBodyArmorRenderer<T extends Item & GeoItem> extends GeoArmorRenderer<T> {
    public FullBodyArmorRenderer(GeoModel<T> model) {
        super(model);
    }

    @Override
    protected void applyBoneVisibilityBySlot(EquipmentSlot slot) {
        setAllVisible(true);
        setAllBonesVisible(true);
    }
}
