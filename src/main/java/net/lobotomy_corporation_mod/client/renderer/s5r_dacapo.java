package net.lobotomy_corporation_mod.client.renderer;

import net.lobotomy_corporation_mod.client.models.s5m_dacapo;
import net.lobotomy_corporation_mod.items.s5dacapo;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class s5r_dacapo extends GeoArmorRenderer<s5dacapo> {
    public s5r_dacapo() {
        super(new s5m_dacapo());
    }
}
