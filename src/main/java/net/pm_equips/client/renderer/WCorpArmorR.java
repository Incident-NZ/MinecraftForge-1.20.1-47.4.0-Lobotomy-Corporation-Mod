package net.pm_equips.client.renderer;

import net.pm_equips.client.models.WCorpArmorM;
import net.pm_equips.items.WCorpArmor;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class WCorpArmorR extends GeoArmorRenderer<WCorpArmor> {
    public WCorpArmorR() {
        super(new WCorpArmorM());
    }
}
