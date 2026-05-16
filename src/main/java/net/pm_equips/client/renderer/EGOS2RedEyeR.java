package net.pm_equips.client.renderer;

import net.pm_equips.client.models.EGOS2RedEyeM;
import net.pm_equips.items.EGOS2RedEye;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class EGOS2RedEyeR extends GeoArmorRenderer<EGOS2RedEye> {
    public EGOS2RedEyeR() {
        super(new EGOS2RedEyeM());
    }
}
