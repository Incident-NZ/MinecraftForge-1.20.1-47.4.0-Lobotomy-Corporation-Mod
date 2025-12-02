package net.lobotomy_corporation_mod;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class TagInit {
    public static class Items {
        public static final TagKey<Item> BULLET_MAGIC = createTag("magic_bullet");
        public static final TagKey<Item> BULLET_RIFLE = createTag("rifle_bullet");
        public static final TagKey<Item> BULLET_PISTOL = createTag("pistol_bullet");
        public static final TagKey<Item> EGO_GIFT = createTag("ego_gift");
        public static final TagKey<Item> E_BOX = createTag("e_box");
        public static final TagKey<Item> EGO = createTag("ego");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(Lobotomy_corporation_mod.MOD_ID, name));
        }
    }
}
