package net.lobotomy_corporation_mod.client.renderer;

import net.lobotomy_corporation_mod.client.models.s4m_lamp;
import net.lobotomy_corporation_mod.items.s4lamp;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class s4r_lamp extends GeoArmorRenderer<s4lamp> {
    public s4r_lamp() {
        super(new s4m_lamp());
    }
}
