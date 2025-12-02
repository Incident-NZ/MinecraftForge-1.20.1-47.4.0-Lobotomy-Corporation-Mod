package net.lobotomy_corporation_mod.client.renderer;

import net.lobotomy_corporation_mod.client.models.s3m_magicbullet;
import net.lobotomy_corporation_mod.items.s3magicbullet;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class s3r_magicbullet extends GeoArmorRenderer<s3magicbullet> {
    public s3r_magicbullet() {
        super(new s3m_magicbullet());
    }
}
