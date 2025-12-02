package net.lobotomy_corporation_mod.client.renderer;

import net.lobotomy_corporation_mod.client.models.s3m_lament;
import net.lobotomy_corporation_mod.items.s3lament;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class s3r_lament extends GeoArmorRenderer<s3lament> {
    public s3r_lament() {
        super(new s3m_lament());
    }
}
