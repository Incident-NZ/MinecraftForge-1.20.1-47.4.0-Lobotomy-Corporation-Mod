package net.pm_equips.client.renderer;

import net.pm_equips.client.models.EGOS3LoggingM;
import net.pm_equips.items.EGOS3Logging;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class EGOS3LoggingR extends GeoArmorRenderer<EGOS3Logging> {
    public EGOS3LoggingR() {
        super(new EGOS3LoggingM());
    }
}
