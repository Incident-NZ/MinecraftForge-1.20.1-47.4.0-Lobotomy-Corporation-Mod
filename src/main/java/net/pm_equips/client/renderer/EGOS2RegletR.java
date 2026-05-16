package net.pm_equips.client.renderer;

import net.pm_equips.client.models.EGOS2RegletM;
import net.pm_equips.items.EGOS2Reglet;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class EGOS2RegletR extends GeoArmorRenderer<EGOS2Reglet> {
    public EGOS2RegletR() {
        super(new EGOS2RegletM());
    }
}
