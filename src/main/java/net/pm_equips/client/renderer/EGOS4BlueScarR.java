package net.pm_equips.client.renderer;

import net.pm_equips.client.models.EGOS4BlueScarM;
import net.pm_equips.items.EGOS4BlueScar;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class EGOS4BlueScarR extends GeoArmorRenderer<EGOS4BlueScar> {
    public EGOS4BlueScarR() {
        super(new EGOS4BlueScarM());
    }
}
