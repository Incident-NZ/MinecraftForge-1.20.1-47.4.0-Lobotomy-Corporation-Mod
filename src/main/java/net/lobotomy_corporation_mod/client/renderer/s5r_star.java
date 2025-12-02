package net.lobotomy_corporation_mod.client.renderer;

import net.lobotomy_corporation_mod.client.models.s5m_star;
import net.lobotomy_corporation_mod.items.s5star;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class s5r_star extends GeoArmorRenderer<s5star> {
    public s5r_star() {
        super(new s5m_star());
    }
}
