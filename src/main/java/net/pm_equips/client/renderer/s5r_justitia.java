package net.pm_equips.client.renderer;

import net.pm_equips.client.models.s5m_justitia;
import net.pm_equips.items.s5justitia;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class s5r_justitia extends GeoArmorRenderer<s5justitia> {
    public s5r_justitia() {
        super(new s5m_justitia());
    }
}
