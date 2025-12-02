package net.lobotomy_corporation_mod.client.renderer;

import net.lobotomy_corporation_mod.client.models.s3m_laetitia;
import net.lobotomy_corporation_mod.items.s3laetitia;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class s3r_laetitia extends GeoArmorRenderer<s3laetitia> {
    public s3r_laetitia() {
        super(new s3m_laetitia());
    }
}
