package net.lobotomy_corporation_mod.client.renderer;

import net.lobotomy_corporation_mod.client.models.s4m_hornet;
import net.lobotomy_corporation_mod.items.s4hornet;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class s4r_hornet extends GeoArmorRenderer<s4hornet> {
    public s4r_hornet() {
        super(new s4m_hornet());
    }
}
