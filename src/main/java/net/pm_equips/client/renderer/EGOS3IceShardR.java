package net.pm_equips.client.renderer;

import net.pm_equips.client.models.EGOS3IceShardM;
import net.pm_equips.items.EGOS3IceShard;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class EGOS3IceShardR extends GeoArmorRenderer<EGOS3IceShard> {
    public EGOS3IceShardR() {
        super(new EGOS3IceShardM());
    }
}
