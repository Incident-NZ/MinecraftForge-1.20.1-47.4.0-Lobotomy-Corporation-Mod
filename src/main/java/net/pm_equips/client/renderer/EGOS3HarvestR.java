package net.pm_equips.client.renderer;

import net.pm_equips.client.models.EGOS3HarvestM;
import net.pm_equips.items.EGOS3Harvest;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class EGOS3HarvestR extends GeoArmorRenderer<EGOS3Harvest> {
    public EGOS3HarvestR() {
        super(new EGOS3HarvestM());
    }
}
