package net.pm_equips.client.renderer;

import net.pm_equips.client.models.EGOS5MimicryM;
import net.pm_equips.items.EGOS5Mimicry;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class EGOS5MimicryR extends GeoArmorRenderer<EGOS5Mimicry> {
    public EGOS5MimicryR() {
        super(new EGOS5MimicryM());
    }
}
