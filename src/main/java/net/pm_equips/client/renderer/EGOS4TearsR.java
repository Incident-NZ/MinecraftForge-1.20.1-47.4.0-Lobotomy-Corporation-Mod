package net.pm_equips.client.renderer;

import net.pm_equips.client.models.EGOS4TearsM;
import net.pm_equips.items.EGOS4Tears;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class EGOS4TearsR extends GeoArmorRenderer<EGOS4Tears> {
    public EGOS4TearsR() {
        super(new EGOS4TearsM());
    }
}
