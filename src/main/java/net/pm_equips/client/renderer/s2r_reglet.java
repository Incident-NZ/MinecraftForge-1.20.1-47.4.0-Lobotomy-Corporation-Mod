package net.pm_equips.client.renderer;

import net.pm_equips.client.models.s2m_reglet;
import net.pm_equips.items.s2reglet;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class s2r_reglet extends GeoArmorRenderer<s2reglet> {
    public s2r_reglet() {
        super(new s2m_reglet());
    }
}
