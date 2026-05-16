package net.pm_equips.client.renderer;

import net.pm_equips.client.models.EGOS5WhitenightM;
import net.pm_equips.items.EGOS5Whitenight;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class EGOS5WhitenightR extends GeoArmorRenderer<EGOS5Whitenight> {
    public EGOS5WhitenightR() {
        super(new EGOS5WhitenightM());
    }
}
