package net.pm_equips.client.renderer;

import net.minecraft.world.entity.EquipmentSlot;
import net.pm_equips.client.models.EGOS4AromaM;
import net.pm_equips.items.EGOP4Aroma;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class EGOS4AromaR extends GeoArmorRenderer<EGOP4Aroma> {
    public EGOS4AromaR() {
        super(new EGOS4AromaM());
    }

    @Override
    protected void applyBoneVisibilityBySlot(EquipmentSlot slot) {
        setAllVisible(false);

        if (slot == EquipmentSlot.LEGS) {
            setAllVisible(true);
            setAllBonesVisible(true);
        }
    }
}
