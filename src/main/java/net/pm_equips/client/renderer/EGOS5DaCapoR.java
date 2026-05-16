package net.pm_equips.client.renderer;

import net.pm_equips.client.models.EGOS5DaCapoM;
import net.pm_equips.items.EGOS5DaCapo;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class EGOS5DaCapoR extends GeoArmorRenderer<EGOS5DaCapo> {
    public EGOS5DaCapoR() {
        super(new EGOS5DaCapoM());
    }
}
