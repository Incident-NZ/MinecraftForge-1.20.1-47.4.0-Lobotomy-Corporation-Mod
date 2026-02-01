package net.pm_equips.client.renderer;

import net.pm_equips.client.models.s2m_lantern;
import net.pm_equips.items.s2lantern;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class s2r_lantern extends GeoArmorRenderer<s2lantern> {
    public s2r_lantern() {
        super(new s2m_lantern());
    }
}
