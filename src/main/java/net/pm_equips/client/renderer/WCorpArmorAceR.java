package net.pm_equips.client.renderer;

import net.minecraft.world.entity.EquipmentSlot;
import net.pm_equips.client.models.WCorpArmorAceM;
import net.pm_equips.items.WCorpArmorAce;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class WCorpArmorAceR extends GeoArmorRenderer<WCorpArmorAce> {
    public WCorpArmorAceR() {
        super(new WCorpArmorAceM());
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
