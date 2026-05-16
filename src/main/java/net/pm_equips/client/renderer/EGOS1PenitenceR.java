package net.pm_equips.client.renderer;

import net.pm_equips.client.models.EGOS1PenitenceM;
import net.pm_equips.items.EGOS1Penitence;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class EGOS1PenitenceR extends GeoArmorRenderer<EGOS1Penitence> {
    public EGOS1PenitenceR() {
        super(new EGOS1PenitenceM());
    }
}
