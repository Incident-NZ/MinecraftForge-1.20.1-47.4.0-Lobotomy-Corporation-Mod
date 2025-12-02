package net.lobotomy_corporation_mod.client.renderer;

import net.lobotomy_corporation_mod.client.models.s2m_beak;
import net.lobotomy_corporation_mod.items.s2beak;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class s2r_beak extends GeoArmorRenderer<s2beak> {
    public s2r_beak() {
        super(new s2m_beak());
    }
}
