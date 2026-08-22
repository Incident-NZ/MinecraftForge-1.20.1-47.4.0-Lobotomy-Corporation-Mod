package net.pm_equips.client.renderer;

import net.pm_equips.client.models.WCorpArmorM;
import net.pm_equips.items.WCorpArmor;

public class WCorpArmorR extends FullBodyArmorRenderer<WCorpArmor> {
    public WCorpArmorR() {
        super(new WCorpArmorM());
    }
}
