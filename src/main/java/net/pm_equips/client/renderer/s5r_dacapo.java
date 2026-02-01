package net.pm_equips.client.renderer;

import net.pm_equips.client.models.s5m_dacapo;
import net.pm_equips.items.s5dacapo;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class s5r_dacapo extends GeoArmorRenderer<s5dacapo> {
    public s5r_dacapo() {
        super(new s5m_dacapo());
    }
}
