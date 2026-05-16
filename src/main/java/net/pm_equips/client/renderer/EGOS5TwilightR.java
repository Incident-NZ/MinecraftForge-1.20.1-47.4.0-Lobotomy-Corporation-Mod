package net.pm_equips.client.renderer;

import net.pm_equips.client.models.EGOS5TwilightM;
import net.pm_equips.items.EGOS5Twilight;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class EGOS5TwilightR extends GeoArmorRenderer<EGOS5Twilight> {
    public EGOS5TwilightR() {
        super(new EGOS5TwilightM());
    }
}
