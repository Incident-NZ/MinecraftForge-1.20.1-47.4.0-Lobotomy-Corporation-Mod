package net.pm_equips.client.renderer;

import net.pm_equips.client.models.s5m_star;
import net.pm_equips.items.s5star;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class s5r_star extends GeoArmorRenderer<s5star> {
    public s5r_star() {
        super(new s5m_star());
    }
}
