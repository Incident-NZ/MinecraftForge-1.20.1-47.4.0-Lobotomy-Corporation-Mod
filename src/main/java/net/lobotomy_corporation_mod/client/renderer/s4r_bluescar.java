package net.lobotomy_corporation_mod.client.renderer;

import net.lobotomy_corporation_mod.client.models.s4m_bluescar;
import net.lobotomy_corporation_mod.items.s4bluescar;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class s4r_bluescar extends GeoArmorRenderer<s4bluescar> {
    public s4r_bluescar() {
        super(new s4m_bluescar());
    }
}
