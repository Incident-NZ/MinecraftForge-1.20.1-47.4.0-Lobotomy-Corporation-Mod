package net.lobotomy_corporation_mod.client.renderer;

import net.lobotomy_corporation_mod.client.models.m5m_t0346;
import net.lobotomy_corporation_mod.entity.m5_t0346;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class m5r_t0346 extends GeoEntityRenderer<m5_t0346> {
    public m5r_t0346(EntityRendererProvider.Context context) {
        super(context, new m5m_t0346());
    }
}
