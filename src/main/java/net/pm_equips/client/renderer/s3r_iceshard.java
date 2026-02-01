package net.pm_equips.client.renderer;

import net.pm_equips.client.models.s3m_iceshard;
import net.pm_equips.items.s3iceshard;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class s3r_iceshard extends GeoArmorRenderer<s3iceshard> {
    public s3r_iceshard() {
        super(new s3m_iceshard());
    }
}
