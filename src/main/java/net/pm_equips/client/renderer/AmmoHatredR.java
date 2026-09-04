package net.pm_equips.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.pm_equips.client.models.EGOMagicM;
import net.pm_equips.entity.EGOHatredMagicP;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class AmmoHatredR extends GeoEntityRenderer<EGOHatredMagicP> {
    public AmmoHatredR(EntityRendererProvider.Context context) {
        super(context, new EGOMagicM());
    }
}
