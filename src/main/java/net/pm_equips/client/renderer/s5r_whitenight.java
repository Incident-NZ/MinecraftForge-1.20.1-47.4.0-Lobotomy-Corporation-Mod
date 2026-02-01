package net.pm_equips.client.renderer;

import net.pm_equips.client.models.s5m_whitenight;
import net.pm_equips.items.s5whitenight;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class s5r_whitenight extends GeoArmorRenderer<s5whitenight> {
    public s5r_whitenight() {
        super(new s5m_whitenight());
    }
}
