package net.pm_equips.client.renderer;

import net.pm_equips.client.models.AmmoExpM;
import net.pm_equips.entity.AmmoExp;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class AmmoExpR extends GeoEntityRenderer<AmmoExp> {
    public AmmoExpR(EntityRendererProvider.Context context) {
        super(context, new AmmoExpM());
    }
}
