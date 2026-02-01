package net.pm_equips.client.renderer;

import net.pm_equips.client.models.s5m_mimicry;
import net.pm_equips.items.s5mimicry;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class s5r_mimicry extends GeoArmorRenderer<s5mimicry> {
    public s5r_mimicry() {
        super(new s5m_mimicry());
    }
}
