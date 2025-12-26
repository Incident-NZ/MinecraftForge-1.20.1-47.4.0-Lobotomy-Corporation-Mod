package net.lobotomy_corporation_mod.items;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class AmmoExplosive extends Item {
    private final ResourceLocation bulletType;

    public AmmoExplosive(ResourceLocation bulletType) {
        super(new Item.Properties().stacksTo(64));
        this.bulletType = bulletType;
    }

    public ResourceLocation getBulletType() {
        return bulletType;
    }
}
