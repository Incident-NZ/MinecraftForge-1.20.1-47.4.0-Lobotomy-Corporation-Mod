package net.lobotomy_corporation_mod.client.models;

import net.lobotomy_corporation_mod.items.s3magicbullet;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;

public class s3m_magicbullet extends GeoModel<s3magicbullet> {
    @Override
    public ResourceLocation getModelResource(s3magicbullet animatable) {
        return new ResourceLocation("lobotomy_corporation_mod", "geo/ego_armor.geo.json");
    }

    @Nullable
    @Override
    public ResourceLocation getTextureResource(s3magicbullet animatable) {
        return new ResourceLocation("lobotomy_corporation_mod", "textures/armor/ego_s3_magic_bullet.png");
    }

    @Override
    public ResourceLocation getAnimationResource(s3magicbullet animatable) {
        return new ResourceLocation("lobotomy_corporation_mod", "animations/ego_armor.animation.json");
    }
}
