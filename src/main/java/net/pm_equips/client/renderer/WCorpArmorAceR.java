package net.pm_equips.client.renderer;

import net.pm_equips.client.models.WCorpArmorAceM;
import net.pm_equips.items.WCorpArmorAce;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class WCorpArmorAceR extends GeoArmorRenderer<WCorpArmorAce> {
    public WCorpArmorAceR() {
        super(new WCorpArmorAceM());
    }
}
