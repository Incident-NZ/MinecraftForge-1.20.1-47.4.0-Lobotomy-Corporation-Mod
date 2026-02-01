package net.pm_equips.client.renderer;

import net.pm_equips.client.models.s3m_magicbullet;
import net.pm_equips.items.s3magicbullet;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class s3r_magicbullet extends GeoArmorRenderer<s3magicbullet> {
    public s3r_magicbullet() {
        super(new s3m_magicbullet());
    }
}
