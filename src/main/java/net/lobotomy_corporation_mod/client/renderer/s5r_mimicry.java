package net.lobotomy_corporation_mod.client.renderer;

import net.lobotomy_corporation_mod.client.models.s5m_mimicry;
import net.lobotomy_corporation_mod.items.s5mimcry;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class s5r_mimicry extends GeoArmorRenderer<s5mimcry> {
    public s5r_mimicry() {
        super(new s5m_mimicry());
    }
}
