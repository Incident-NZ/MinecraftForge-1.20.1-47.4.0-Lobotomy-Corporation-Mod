package net.pm_equips.client.renderer;

import net.pm_equips.client.models.HeadClawM;
import net.pm_equips.items.HeadClaw;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class HeadClawR extends GeoItemRenderer<HeadClaw> {
    public  HeadClawR() {
        super(new HeadClawM());
    }
}
