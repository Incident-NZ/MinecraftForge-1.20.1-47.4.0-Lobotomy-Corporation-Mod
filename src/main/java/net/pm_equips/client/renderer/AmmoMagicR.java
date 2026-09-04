package net.pm_equips.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.pm_equips.client.models.AmmoMagicM;
import net.pm_equips.entity.EGOMagicP;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class AmmoMagicR extends GeoEntityRenderer<EGOMagicP> {
    public AmmoMagicR(EntityRendererProvider.Context context) {
        super(context, new AmmoMagicM());
    }
}