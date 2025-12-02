package net.lobotomy_corporation_mod.client.renderer;

import net.lobotomy_corporation_mod.client.models.s2m_redeye;
import net.lobotomy_corporation_mod.items.s2redeye;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class s2r_redeye extends GeoArmorRenderer<s2redeye> {
    public s2r_redeye() {
        super(new s2m_redeye());
    }
}
