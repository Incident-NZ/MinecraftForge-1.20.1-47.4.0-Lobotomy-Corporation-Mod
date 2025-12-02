package net.lobotomy_corporation_mod.client.renderer;

import net.lobotomy_corporation_mod.client.models.s5m_twilight;
import net.lobotomy_corporation_mod.items.s5twilight;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class s5r_twilight extends GeoArmorRenderer<s5twilight> {
    public s5r_twilight() {
        super(new s5m_twilight());
    }
}
