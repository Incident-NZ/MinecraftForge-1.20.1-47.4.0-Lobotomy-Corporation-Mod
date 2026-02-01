package net.pm_equips.client.renderer;

import net.pm_equips.client.models.s3m_laetitia;
import net.pm_equips.items.s3laetitia;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class s3r_laetitia extends GeoArmorRenderer<s3laetitia> {
    public s3r_laetitia() {
        super(new s3m_laetitia());
    }
}
