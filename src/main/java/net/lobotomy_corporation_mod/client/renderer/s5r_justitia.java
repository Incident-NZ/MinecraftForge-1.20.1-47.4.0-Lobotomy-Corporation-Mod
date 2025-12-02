package net.lobotomy_corporation_mod.client.renderer;

import net.lobotomy_corporation_mod.client.models.s5m_justitia;
import net.lobotomy_corporation_mod.items.s5justitia;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class s5r_justitia extends GeoArmorRenderer<s5justitia> {
    public s5r_justitia() {
        super(new s5m_justitia());
    }
}
