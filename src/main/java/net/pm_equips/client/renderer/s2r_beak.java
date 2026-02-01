package net.pm_equips.client.renderer;

import net.pm_equips.client.models.s2m_beak;
import net.pm_equips.items.s2beak;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class s2r_beak extends GeoArmorRenderer<s2beak> {
    public s2r_beak() {
        super(new s2m_beak());
    }
}
