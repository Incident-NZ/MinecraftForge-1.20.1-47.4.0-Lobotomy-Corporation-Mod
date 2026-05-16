package net.pm_equips.client.renderer;

import net.pm_equips.client.models.EGOS2LanternM;
import net.pm_equips.items.EGOS2Lantern;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class EGOS2LanternR extends GeoArmorRenderer<EGOS2Lantern> {
    public EGOS2LanternR() {
        super(new EGOS2LanternM());
    }
}
