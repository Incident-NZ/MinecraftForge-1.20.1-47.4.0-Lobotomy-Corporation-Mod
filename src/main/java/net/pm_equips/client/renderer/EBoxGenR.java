package net.pm_equips.client.renderer;

import net.pm_equips.client.models.EBoxGenM;
import net.pm_equips.blockentity.EBoxGenBlockEntity;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class EBoxGenR extends GeoBlockRenderer<EBoxGenBlockEntity> {
    public EBoxGenR() {
        super(new EBoxGenM());
    }
}

