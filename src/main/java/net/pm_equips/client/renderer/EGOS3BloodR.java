package net.pm_equips.client.renderer;

import net.pm_equips.client.models.EGOS3BloodM;
import net.pm_equips.items.EGOS3Blood;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class EGOS3BloodR extends GeoArmorRenderer<EGOS3Blood> {
    public EGOS3BloodR() {
        super(new EGOS3BloodM());
    }
}
