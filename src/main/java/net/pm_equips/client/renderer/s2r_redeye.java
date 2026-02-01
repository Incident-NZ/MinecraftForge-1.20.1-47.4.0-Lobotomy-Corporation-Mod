package net.pm_equips.client.renderer;

import net.pm_equips.client.models.s2m_redeye;
import net.pm_equips.items.s2redeye;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class s2r_redeye extends GeoArmorRenderer<s2redeye> {
    public s2r_redeye() {
        super(new s2m_redeye());
    }
}
