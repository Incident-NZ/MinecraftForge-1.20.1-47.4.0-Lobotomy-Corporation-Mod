package net.pm_equips.client.renderer;

import net.pm_equips.client.models.EGOS5SmileM;
import net.pm_equips.items.EGOS5Smile;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class EGOS5SmileR extends GeoArmorRenderer<EGOS5Smile> {
    public EGOS5SmileR() {
        super(new EGOS5SmileM());
    }
}
