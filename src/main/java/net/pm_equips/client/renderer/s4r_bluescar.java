package net.pm_equips.client.renderer;

import net.pm_equips.client.models.s4m_bluescar;
import net.pm_equips.items.s4bluescar;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class s4r_bluescar extends GeoArmorRenderer<s4bluescar> {
    public s4r_bluescar() {
        super(new s4m_bluescar());
    }
}
