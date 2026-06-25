package net.pm_equips.client.renderer;

import net.pm_equips.client.models.EGOS3MK4M;
import net.pm_equips.items.EGOS3MK4;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class EGOS3MK4R extends GeoArmorRenderer<EGOS3MK4> {
    public EGOS3MK4R() {
        super(new EGOS3MK4M());
    }
}
