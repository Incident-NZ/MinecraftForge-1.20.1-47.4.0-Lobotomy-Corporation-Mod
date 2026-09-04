package net.pm_equips.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.pm_equips.client.models.AmmoLogicRVM;
import net.pm_equips.entity.AmmoLogicRV;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class AmmoLARVR extends GeoEntityRenderer<AmmoLogicRV> {
    public AmmoLARVR(EntityRendererProvider.Context context) {
        super(context, new AmmoLogicRVM());
    }
}
