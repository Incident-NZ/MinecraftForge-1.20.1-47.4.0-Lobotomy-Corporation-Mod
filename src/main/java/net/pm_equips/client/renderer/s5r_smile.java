package net.pm_equips.client.renderer;

import net.pm_equips.client.models.s5m_smile;
import net.pm_equips.items.s5smile;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class s5r_smile extends GeoArmorRenderer<s5smile> {
    public s5r_smile() {
        super(new s5m_smile());
    }
}
