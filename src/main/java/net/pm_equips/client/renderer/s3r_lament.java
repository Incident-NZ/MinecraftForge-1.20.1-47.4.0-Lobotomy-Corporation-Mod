package net.pm_equips.client.renderer;

import net.pm_equips.client.models.s3m_lament;
import net.pm_equips.items.s3lament;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class s3r_lament extends GeoArmorRenderer<s3lament> {
    public s3r_lament() {
        super(new s3m_lament());
    }
}
