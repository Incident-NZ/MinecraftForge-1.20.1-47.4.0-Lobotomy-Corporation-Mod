package net.lobotomy_corporation_mod.client.renderer;

import net.lobotomy_corporation_mod.client.models.s5m_whitenight;
import net.lobotomy_corporation_mod.items.s5whitenight;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class s5r_whitenight extends GeoArmorRenderer<s5whitenight> {
    public s5r_whitenight() {
        super(new s5m_whitenight());
    }
}
