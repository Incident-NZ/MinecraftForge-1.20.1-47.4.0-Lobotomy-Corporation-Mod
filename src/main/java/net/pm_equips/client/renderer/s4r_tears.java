package net.pm_equips.client.renderer;

import net.pm_equips.client.models.s4m_tears;
import net.pm_equips.items.s4tears;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class s4r_tears extends GeoArmorRenderer<s4tears> {
    public s4r_tears() {
        super(new s4m_tears());
    }
}
