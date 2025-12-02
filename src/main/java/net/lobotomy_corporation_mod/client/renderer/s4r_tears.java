package net.lobotomy_corporation_mod.client.renderer;

import net.lobotomy_corporation_mod.client.models.s4m_tears;
import net.lobotomy_corporation_mod.items.s4tears;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class s4r_tears extends GeoArmorRenderer<s4tears> {
    public s4r_tears() {
        super(new s4m_tears());
    }
}
