package net.pm_equips.client.renderer;

import net.pm_equips.client.models.EGOS4LampM;
import net.pm_equips.items.EGOS4Lamp;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class EGOS4LampR extends GeoArmorRenderer<EGOS4Lamp> {
    public EGOS4LampR() {
        super(new EGOS4LampM());
    }
}
