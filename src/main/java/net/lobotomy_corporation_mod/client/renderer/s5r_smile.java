package net.lobotomy_corporation_mod.client.renderer;

import net.lobotomy_corporation_mod.client.models.s5m_smile;
import net.lobotomy_corporation_mod.items.s5smile;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class s5r_smile extends GeoArmorRenderer<s5smile> {
    public s5r_smile() {
        super(new s5m_smile());
    }
}
