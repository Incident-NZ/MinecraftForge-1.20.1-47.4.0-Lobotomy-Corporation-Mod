package net.pm_equips.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.pm_equips.client.models.AmmoLogicSGM;
import net.pm_equips.entity.AmmoLogicSG;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class AmmoLASGR extends GeoEntityRenderer<AmmoLogicSG> {

    public AmmoLASGR(EntityRendererProvider.Context context) {
        super(context, new AmmoLogicSGM());
    }
}
