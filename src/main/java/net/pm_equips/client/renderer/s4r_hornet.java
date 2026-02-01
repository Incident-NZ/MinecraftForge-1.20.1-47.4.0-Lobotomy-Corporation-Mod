package net.pm_equips.client.renderer;

import net.pm_equips.client.models.s4m_hornet;
import net.pm_equips.items.s4hornet;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class s4r_hornet extends GeoArmorRenderer<s4hornet> {
    public s4r_hornet() {
        super(new s4m_hornet());
    }
}
