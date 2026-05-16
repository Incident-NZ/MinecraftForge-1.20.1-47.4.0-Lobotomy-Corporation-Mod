package net.pm_equips.client.renderer;

import net.pm_equips.client.models.EGOS5JustitiaM;
import net.pm_equips.items.EGOS5Justitia;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class EGOS5JustitiaR extends GeoArmorRenderer<EGOS5Justitia> {
    public EGOS5JustitiaR() {
        super(new EGOS5JustitiaM());
    }
}
