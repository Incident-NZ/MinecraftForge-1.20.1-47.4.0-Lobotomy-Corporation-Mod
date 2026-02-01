package net.pm_equips.client.renderer;

import net.pm_equips.client.models.s5m_twilight;
import net.pm_equips.items.s5twilight;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class s5r_twilight extends GeoArmorRenderer<s5twilight> {
    public s5r_twilight() {
        super(new s5m_twilight());
    }
}
