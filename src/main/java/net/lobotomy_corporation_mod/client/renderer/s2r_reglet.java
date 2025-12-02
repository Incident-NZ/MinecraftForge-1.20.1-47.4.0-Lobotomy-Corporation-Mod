package net.lobotomy_corporation_mod.client.renderer;

import net.lobotomy_corporation_mod.client.models.s2m_reglet;
import net.lobotomy_corporation_mod.items.s2reglet;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class s2r_reglet extends GeoArmorRenderer<s2reglet> {
    public s2r_reglet() {
        super(new s2m_reglet());
    }
}
