package net.pm_equips.client.renderer;

import net.pm_equips.client.models.EGOS4HornetM;
import net.pm_equips.items.EGOS4Hornet;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class EGOS4HornetR extends GeoArmorRenderer<EGOS4Hornet> {
    public EGOS4HornetR() {
        super(new EGOS4HornetM());
    }
}
