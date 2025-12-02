package net.lobotomy_corporation_mod.client.renderer;

import net.lobotomy_corporation_mod.client.models.s2m_lantern;
import net.lobotomy_corporation_mod.items.s2lantern;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class s2r_lantern extends GeoArmorRenderer<s2lantern> {
    public s2r_lantern() {
        super(new s2m_lantern());
    }
}
