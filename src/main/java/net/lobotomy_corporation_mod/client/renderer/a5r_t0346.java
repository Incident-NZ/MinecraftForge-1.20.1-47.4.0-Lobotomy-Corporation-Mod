package net.lobotomy_corporation_mod.client.renderer;

import net.lobotomy_corporation_mod.client.models.a5m_t0346;
import net.lobotomy_corporation_mod.entity.a5_t0346;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class a5r_t0346 extends GeoEntityRenderer<a5_t0346> {
    public a5r_t0346(EntityRendererProvider.Context context) {
        super(context, new a5m_t0346());
    }
}
