package net.pm_equips.client.renderer;

import net.pm_equips.client.models.s1m_penitence;
import net.pm_equips.items.s1penitence;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class s1r_penitence extends GeoArmorRenderer<s1penitence> {
    public s1r_penitence() {
        super(new s1m_penitence());
    }
}
