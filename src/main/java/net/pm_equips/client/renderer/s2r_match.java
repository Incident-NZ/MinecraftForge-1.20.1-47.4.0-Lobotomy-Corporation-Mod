package net.pm_equips.client.renderer;

import net.pm_equips.client.models.s2m_match;
import net.pm_equips.items.s2match;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class s2r_match extends GeoArmorRenderer<s2match> {
    public s2r_match() {
        super(new s2m_match());
    }
}
