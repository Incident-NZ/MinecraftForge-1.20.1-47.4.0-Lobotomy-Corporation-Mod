package net.lobotomy_corporation_mod.client.renderer;

import net.lobotomy_corporation_mod.client.models.s2m_match;
import net.lobotomy_corporation_mod.items.s2match;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class s2r_match extends GeoArmorRenderer<s2match> {
    public s2r_match() {
        super(new s2m_match());
    }
}
