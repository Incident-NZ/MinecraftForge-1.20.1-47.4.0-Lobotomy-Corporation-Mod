package net.lobotomy_corporation_mod.client.renderer;

import net.lobotomy_corporation_mod.client.models.s1m_penitence;
import net.lobotomy_corporation_mod.items.s1penitence;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class s1r_penitence extends GeoArmorRenderer<s1penitence> {
    public s1r_penitence() {
        super(new s1m_penitence());
    }
}
