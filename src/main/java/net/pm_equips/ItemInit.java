package net.pm_equips;

import net.pm_equips.items.*;
import net.pm_equips.items.materials.ArmorEquips;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, PMEquipsMain.MOD_ID);

    //アイテム 弾薬
    public static final RegistryObject<Item> MAGIC_BULLET_AMMO = ITEMS.register("weapon_bullet_magic",
            () -> new AmmoMagicBullet(new ResourceLocation("pm_equips", "magic_bullet")));
    public static final RegistryObject<Item> RIFLE_BULLET_AMMO = ITEMS.register("weapon_bullet_rifle",
            () -> new AmmoRifle(new ResourceLocation("pm_equips", "rifle_bullet")));
    public static final RegistryObject<Item> PISTOL_BULLET_AMMO = ITEMS.register("weapon_bullet_pistol",
            () -> new AmmoPistol(new ResourceLocation("pm_equips", "pistol_bullet")));
    public static final RegistryObject<Item> EXPLOSIVE_BULLET_AMMO = ITEMS.register("weapon_bullet_explosive",
            () -> new AmmoExplosive(new ResourceLocation("pm_equips", "explosive_bullet")));

    //L社EGO武器 近距離
    public static final RegistryObject<Item> W5_TWILIGHT = ITEMS.register("w5_twilight", W5Twilight::new);
    public static final RegistryObject<Item> W5_PARADISE_LOST = ITEMS.register("w5_whitenight", W5WhiteNight::new);
    public static final RegistryObject<Item> W5_MIMICRY = ITEMS.register("w5_mimicry", W5Mimicry::new);
    public static final RegistryObject<Item> W5_DA_CAPO = ITEMS.register("w5_da_capo", W5DaCapo::new);
    public static final RegistryObject<Item> W5_SMILE = ITEMS.register("w5_smile", W5Smile::new);
    public static final RegistryObject<Item> W5_SOUND_OF_A_STAR = ITEMS.register("w5_star", W5Star::new);
    public static final RegistryObject<Item> W5_JUSTITIA = ITEMS.register("w5_justitia", W5Justitia::new);
    public static final RegistryObject<Item> W4_THE_SWORD_SHARPENED_WITH_TEARS = ITEMS.register("w4_tears", W4Tears::new);
    public static final RegistryObject<Item> W4_LAMP = ITEMS.register("w4_lamp", W4Lamp::new);
    public static final RegistryObject<Item> W4_BLUE_SCAR = ITEMS.register("w4_blue_scar", W4BlueScar::new);
    public static final RegistryObject<Item> W3_BLOOD = ITEMS.register("w3_blood", W3Blood::new);
    public static final RegistryObject<Item> W3_HARVEST = ITEMS.register("w3_harvest", W3Harvest::new);
    public static final RegistryObject<Item> W3_LOGGING = ITEMS.register("w3_logging", W3Logging::new);
    public static final RegistryObject<Item> W3_GALAXY = ITEMS.register("w3_galaxy", W3Galaxy::new);
    public static final RegistryObject<Item> W3_GRINDER_MK4 = ITEMS.register("w3_mk4", W3MK4::new);
    public static final RegistryObject<Item> W3_ICE_SHARD = ITEMS.register("w3_ice_shard", W3IceShard::new);
    public static final RegistryObject<Item> W2_REGLET = ITEMS.register("w2_reglet", W2Reglet::new);
    public static final RegistryObject<Item> W2_RED_EYE = ITEMS.register("w2_red_eye", W2RedEye::new);
    public static final RegistryObject<Item> W2_LANTERN = ITEMS.register("w2_lantern", W2Lantern::new);
    public static final RegistryObject<Item> W1_PENITENCE = ITEMS.register("w1_penitence", W1Penitence::new);
    public static final RegistryObject<Item> G_MIMICRY = ITEMS.register("ex_mimicry", WeaponKaliMimicry::new);
    public static final RegistryObject<Item> EX_DURANDAL = ITEMS.register("ex_durandal", WeaponRolandDurandal::new);
    public static final RegistryObject<Item> EX_ARGALIA = ITEMS.register("ex_blue_kichigai", WeaponArgalia::new);

    //L社EGO武器 遠距離
    public static final RegistryObject<Item> W4_SOLEMN_LAMENT_L = ITEMS.register("w4_lament_l", W4LamentL::new);
    public static final RegistryObject<Item> W4_SOLEMN_LAMENT_R = ITEMS.register("w4_lament_r",
            () -> new W4LamentR(new Item.Properties().durability(3000)));
    public static final RegistryObject<Item> W4_MAGIC_BULLET = ITEMS.register("w4_magic_bullet",
            () -> new W4MagicBullet(new Item.Properties().durability(3000)));
    public static final RegistryObject<Item> W4_HORNET = ITEMS.register("w4_hornet",
            () -> new W4Hornet(new Item.Properties().durability(3000)));
    public static final RegistryObject<Item> W3_HARMONY = ITEMS.register("w3_harmony",
            () -> new W3Harmony(new Item.Properties().durability(2000)));
    public static final RegistryObject<Item> W3_LAETITIA = ITEMS.register("w3_laetitia",
            () -> new W3Laetitia(new Item.Properties().durability(2000)));
    public static final RegistryObject<Item> W2_BEAK = ITEMS.register("w2_beak",
            () -> new W2Beak(new Item.Properties().durability(1000)));
    public static final RegistryObject<Item> W2_FOURTH_MATCH_FIRE = ITEMS.register("w2_match",
            () -> new W2Match(new Item.Properties().durability(1000)));

    //L社EGO防具
    public static final RegistryObject<Item> A5_TWILIGHT_1 = ITEMS.register("s5_twilight_1",
            () -> new s5twilight(ArmorEquips.A5_TWILIGHT, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A5_TWILIGHT_2 = ITEMS.register("s5_twilight_2",
            () -> new s5twilight(ArmorEquips.A5_TWILIGHT, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A5_PARADISE_LOST_1 = ITEMS.register("s5_whitenight_1",
            () -> new s5whitenight(ArmorEquips.A5_PARADISE_LOST, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A5_PARADISE_LOST_2 = ITEMS.register("s5_whitenight_2",
            () -> new s5whitenight(ArmorEquips.A5_PARADISE_LOST, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A5_MIMICRY_2 = ITEMS.register("s5_mimicry_1",
            () -> new s5mimicry(ArmorEquips.A5_MIMICRY, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A5_MIMICRY_3 = ITEMS.register("s5_mimicry_2",
            () -> new s5mimicry(ArmorEquips.A5_MIMICRY, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A5_DA_CAPO_2 = ITEMS.register("s5_da_capo_1",
            () -> new s5dacapo(ArmorEquips.A5_DA_CAPO, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A5_DA_CAPO_3 = ITEMS.register("s5_da_capo_2",
            () -> new s5dacapo(ArmorEquips.A5_DA_CAPO, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A5_SMILE_2 = ITEMS.register("s5_smile_1",
            () -> new s5smile(ArmorEquips.A5_SMILE, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A5_SMILE_3 = ITEMS.register("s5_smile_2",
            () -> new s5smile(ArmorEquips.A5_SMILE, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A5_SOUND_OF_A_STAR_2 = ITEMS.register("s5_star_1",
            () -> new s5star(ArmorEquips.A5_SOUND_OF_A_STAR, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A5_SOUND_OF_A_STAR_3 = ITEMS.register("s5_star_2",
            () -> new s5star(ArmorEquips.A5_SOUND_OF_A_STAR, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A5_JUSTITIA_2 = ITEMS.register("s5_justitia_1",
            () -> new s5justitia(ArmorEquips.A5_JUSTITIA, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A5_JUSTITIA_3 = ITEMS.register("s5_justitia_2",
            () -> new s5justitia(ArmorEquips.A5_JUSTITIA, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A4_THE_SWORD_SHARPENED_WITH_TEARS_2 = ITEMS.register("s4_tears_1",
            () -> new s4tears(ArmorEquips.A4_THE_SWORD_SHARPENED_WITH_TEARS, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A4_THE_SWORD_SHARPENED_WITH_TEARS_3 = ITEMS.register("s4_tears_2",
            () -> new s4tears(ArmorEquips.A4_THE_SWORD_SHARPENED_WITH_TEARS, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A4_LAMP_2 = ITEMS.register("s4_lamp_1",
            () -> new s4lamp(ArmorEquips.A4_LAMP, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A4_LAMP_3 = ITEMS.register("s4_lamp_2",
            () -> new s4lamp(ArmorEquips.A4_LAMP, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A4_HORNET_2 = ITEMS.register("s4_hornet_1",
            () -> new s4hornet(ArmorEquips.A4_HORNET, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A4_HORNET_3 = ITEMS.register("s4_hornet_2",
            () -> new s4hornet(ArmorEquips.A4_HORNET, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A4_BLUE_SCAR_2 = ITEMS.register("s4_blue_scar_1",
            () -> new s4bluescar(ArmorEquips.A4_BLUE_SCAR, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A4_BLUE_SCAR_3 = ITEMS.register("s4_blue_scar_2",
            () -> new s4bluescar(ArmorEquips.A4_BLUE_SCAR, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A3_MAGIC_BULLET_2 = ITEMS.register("s3_magic_bullet_1",
            () -> new s3magicbullet(ArmorEquips.A3_MAGIC_BULLET, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A3_MAGIC_BULLET_3 = ITEMS.register("s3_magic_bullet_2",
            () -> new s3magicbullet(ArmorEquips.A3_MAGIC_BULLET, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A3_SOLEMN_LAMENT_1 = ITEMS.register("s3_lament_1",
            () -> new s3magicbullet(ArmorEquips.A3_SOLEMN_LAMENT, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A3_SOLEMN_LAMENT_2 = ITEMS.register("s3_lament_2",
            () -> new s3magicbullet(ArmorEquips.A3_SOLEMN_LAMENT, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A3_GALAXY_1 = ITEMS.register("s3_galaxy_1",
            () -> new s3magicbullet(ArmorEquips.A3_GALAXY, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A3_GALAXY_2 = ITEMS.register("s3_galaxy_2",
            () -> new s3magicbullet(ArmorEquips.A3_GALAXY, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A3_BLOOD_1 = ITEMS.register("s3_blood_1",
            () -> new s3magicbullet(ArmorEquips.A3_BLOOD, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A3_BLOOD_2 = ITEMS.register("s3_blood_2",
            () -> new s3magicbullet(ArmorEquips.A3_BLOOD, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A3_LOGGING_1 = ITEMS.register("s3_logging_1",
            () -> new s3magicbullet(ArmorEquips.A3_LOGGING, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A3_LOGGING_2 = ITEMS.register("s3_logging_2",
            () -> new s3magicbullet(ArmorEquips.A3_LOGGING, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A3_HARVEST_1 = ITEMS.register("s3_harvest_1",
            () -> new s3magicbullet(ArmorEquips.A3_HARVEST, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A3_HARVEST_2 = ITEMS.register("s3_harvest_2",
            () -> new s3magicbullet(ArmorEquips.A3_HARVEST, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A3_GRINDER_MK4_2 = ITEMS.register("s3_mk4_1",
            () -> new s3magicbullet(ArmorEquips.A3_GRINDER_MK4, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A3_GRINDER_MK4_3 = ITEMS.register("s3_mk4_2",
            () -> new s3magicbullet(ArmorEquips.A3_GRINDER_MK4, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A3_ICE_SHARD_2 = ITEMS.register("s3_ice_shard_1",
            () -> new s3iceshard(ArmorEquips.A3_ICE_SHARD, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A3_ICE_SHARD_3 = ITEMS.register("s3_ice_shard_2",
            () -> new s3iceshard(ArmorEquips.A3_ICE_SHARD, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A3_LAETITIA_2 = ITEMS.register("s3_laetitia_1",
            () -> new s3laetitia(ArmorEquips.A3_LAETITIA, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A3_LAETITIA_3 = ITEMS.register("s3_laetitia_2",
            () -> new s3laetitia(ArmorEquips.A3_LAETITIA, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A2_REGLET_2 = ITEMS.register("s2_reglet_1",
            () -> new s2reglet(ArmorEquips.A2_REGLET, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A2_REGLET_3 = ITEMS.register("s2_reglet_2",
            () -> new s2reglet(ArmorEquips.A2_REGLET, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A2_RED_EYE_2 = ITEMS.register("s2_red_eye_1",
            () -> new s2redeye(ArmorEquips.A2_RED_EYE, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A2_RED_EYE_3 = ITEMS.register("s2_red_eye_2",
            () -> new s2redeye(ArmorEquips.A2_RED_EYE, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A2_BEAK_1 = ITEMS.register("s2_beak_1",
            () -> new s2beak(ArmorEquips.A2_BEAK, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A2_BEAK_2 = ITEMS.register("s2_beak_2",
            () -> new s2beak(ArmorEquips.A2_BEAK, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A2_FOURTH_MATCH_FIRE_1 = ITEMS.register("s2_match_1",
            () -> new s2match(ArmorEquips.A2_FOURTH_MATCH_FIRE, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A2_FOURTH_MATCH_FIRE_2 = ITEMS.register("s2_match_2",
            () -> new s2match(ArmorEquips.A2_FOURTH_MATCH_FIRE, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A2_LANTERN_1 = ITEMS.register("s2_lantern_1",
            () -> new s2lantern(ArmorEquips.A2_LANTERN, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A2_LANTERN_2 = ITEMS.register("s2_lantern_2",
            () -> new s2lantern(ArmorEquips.A2_LANTERN, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A1_PENITENCE_2 = ITEMS.register("s1_penitence_1",
            () -> new EGOS1Penitence(ArmorEquips.A1_PENITENCE, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A1_PENITENCE_3 = ITEMS.register("s1_penitence_2",
            () -> new EGOS1Penitence(ArmorEquips.A1_PENITENCE, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    //W社装備
    public static final RegistryObject<Item> WCORP_ARMOR_1 = ITEMS.register("wcorp_armor_1",
            () -> new WCorpArmor(ArmorEquips.WCORP_ARMOR, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> WCORP_ARMOR_2 = ITEMS.register("wcorp_armor_2",
            () -> new WCorpArmor(ArmorEquips.WCORP_ARMOR, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> WCORP_ARMOR_ACE_1 = ITEMS.register("wcorp_armor_ace_1",
            () -> new WCorpArmorAce(ArmorEquips.WCORP_ARMOR_ACE, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> WCORP_ARMOR_ACE_2 = ITEMS.register("wcorp_armor_ace_2",
            () -> new WCorpArmorAce(ArmorEquips.WCORP_ARMOR_ACE, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));
}
