package net.pm_equips.client.renderer;

import net.pm_equips.client.models.EGOS3LamentM;
import net.pm_equips.items.EGOS3Lament;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class EGOS3LamentR extends GeoArmorRenderer<EGOS3Lament> {
    public EGOS3LamentR() {
        super(new EGOS3LamentM());
    }
}
