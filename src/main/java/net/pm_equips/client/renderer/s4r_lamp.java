package net.pm_equips.client.renderer;

import net.pm_equips.client.models.s4m_lamp;
import net.pm_equips.items.s4lamp;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class s4r_lamp extends GeoArmorRenderer<s4lamp> {
    public s4r_lamp() {
        super(new s4m_lamp());
    }
}
