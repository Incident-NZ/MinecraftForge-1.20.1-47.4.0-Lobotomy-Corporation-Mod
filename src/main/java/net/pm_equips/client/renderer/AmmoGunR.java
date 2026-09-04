package net.pm_equips.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.pm_equips.client.models.AmmoGunM;
import net.pm_equips.entity.AmmoGun;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class AmmoGunR extends GeoEntityRenderer<AmmoGun> {
    public AmmoGunR(EntityRendererProvider.Context context) {
        super(context, new AmmoGunM());
    }
}