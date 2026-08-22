package net.pm_equips.client.renderer;

import net.pm_equips.client.models.KCorpAgentArmorM;
import net.pm_equips.items.KCorpAgentArmor;

public class KCorpAgentArmorR extends FullBodyArmorRenderer<KCorpAgentArmor> {
    public KCorpAgentArmorR() {
        super(new KCorpAgentArmorM());
    }
}
