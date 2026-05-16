package net.pm_equips.client.renderer;

import net.pm_equips.client.models.EGOS3MagicBulletM;
import net.pm_equips.items.EGOS3MagicBullet;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class EGOS3MagicBulletR extends GeoArmorRenderer<EGOS3MagicBullet> {
    public EGOS3MagicBulletR() {
        super(new EGOS3MagicBulletM());
    }
}
