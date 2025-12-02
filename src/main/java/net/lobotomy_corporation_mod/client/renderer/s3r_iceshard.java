package net.lobotomy_corporation_mod.client.renderer;

import net.lobotomy_corporation_mod.client.models.s3m_iceshard;
import net.lobotomy_corporation_mod.items.s3iceshard;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class s3r_iceshard extends GeoArmorRenderer<s3iceshard> {
    public s3r_iceshard() {
        super(new s3m_iceshard());
    }
}
