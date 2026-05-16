package net.pm_equips.client.renderer;

import net.pm_equips.client.models.EGOS2BeakM;
import net.pm_equips.items.EGOS2Beak;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class EGOS2BeakR extends GeoArmorRenderer<EGOS2Beak> {
    public EGOS2BeakR() {
        super(new EGOS2BeakM());
    }
}
