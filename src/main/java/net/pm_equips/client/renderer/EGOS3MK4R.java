package net.pm_equips.client.renderer;

import net.minecraft.world.entity.EquipmentSlot;
import net.pm_equips.client.models.EGOS3MK4M;
import net.pm_equips.items.EGOP3MK4;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class EGOS3MK4R extends GeoArmorRenderer<EGOP3MK4> {
    public EGOS3MK4R() {
        super(new EGOS3MK4M());
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
