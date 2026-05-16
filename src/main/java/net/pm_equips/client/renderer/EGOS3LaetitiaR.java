package net.pm_equips.client.renderer;

import net.pm_equips.client.models.EGOS3LaetitiaM;
import net.pm_equips.items.EGOS3Laetitia;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class EGOS3LaetitiaR extends GeoArmorRenderer<EGOS3Laetitia> {
    public EGOS3LaetitiaR() {
        super(new EGOS3LaetitiaM());
    }
}
