package net.pm_equips;

import net.pm_equips.items.*;
import net.pm_equips.items.materials.ArmorEquips;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, PMEquipsMain.MOD_ID);

    //アイテム
    public static final RegistryObject<Item> ITEM_ENKEPHALIN = ITEMS.register("item_enkephalin",
            () -> new ItemEnkephalin(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> SILVER_INGOT = ITEMS.register("item_silver",
            () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> METAL_INGOT = ITEMS.register("item_metal",
            () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> PLATINUM_INGOT = ITEMS.register("item_platinum",
            () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> BLADE_GEAR = ITEMS.register("item_blade_gear",
            () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> BATTERY = ITEMS.register("item_battery",
            () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> MAGIC_BULLET_AMMO = ITEMS.register("item_bullet_ego",
            () -> new AmmoMagicBullet(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> RIFLE_BULLET_AMMO = ITEMS.register("item_bullet_r",
            () -> new AmmoRifle(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> PISTOL_BULLET_AMMO = ITEMS.register("item_bullet_p",
            () -> new AmmoPistol(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> EXPLOSIVE_BULLET_AMMO = ITEMS.register("item_bullet_ex",
            () -> new AmmoExplosive(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> P_BULLET_LARV = ITEMS.register("item_bullet_larv",
            () -> new AmmoLARV(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> P_BULLET_LASG = ITEMS.register("item_bullet_lasg",
            () -> new AmmoLASG(new Item.Properties().stacksTo(64)));

    //L社EGO武器 近距離
    public static final RegistryObject<Item> W5_TWILIGHT = ITEMS.register("w5_twilight", EGOW5Twilight::new);
    public static final RegistryObject<Item> W5_PARADISE_LOST = ITEMS.register("w5_whitenight", EGOW5WhiteNight::new);
    public static final RegistryObject<Item> W5_MIMICRY = ITEMS.register("w5_mimicry", EGOW5Mimicry::new);
    public static final RegistryObject<Item> W5_DA_CAPO = ITEMS.register("w5_da_capo", EGOW5DaCapo::new);
    public static final RegistryObject<Item> W5_SMILE = ITEMS.register("w5_smile", EGOW5Smile::new);
    public static final RegistryObject<Item> W5_SOUND_OF_A_STAR = ITEMS.register("w5_star", EGOW5Star::new);
    public static final RegistryObject<Item> W5_JUSTITIA = ITEMS.register("w5_justitia", EGOW5Justitia::new);
    public static final RegistryObject<Item> W4_THE_SWORD_SHARPENED_WITH_TEARS = ITEMS.register("w4_tears", EGOW4Tears::new);
    public static final RegistryObject<Item> W4_LAMP = ITEMS.register("w4_lamp", EGOW4Lamp::new);
    public static final RegistryObject<Item> W4_BLUE_SCAR = ITEMS.register("w4_blue_scar", EGOW4BlueScar::new);
    public static final RegistryObject<Item> W3_BLOOD = ITEMS.register("w3_blood", EGOW3Blood::new);
    public static final RegistryObject<Item> W3_HARVEST = ITEMS.register("w3_harvest", EGOW3Harvest::new);
    public static final RegistryObject<Item> W3_LOGGING = ITEMS.register("w3_logging", EGOW3Logging::new);
    public static final RegistryObject<Item> W3_GALAXY = ITEMS.register("w3_galaxy", EGOW3Galaxy::new);
    public static final RegistryObject<Item> W3_GRINDER_MK4 = ITEMS.register("w3_mk4", EGOW3MK4::new);
    public static final RegistryObject<Item> W3_ICE_SHARD = ITEMS.register("w3_ice_shard", EGOW3IceShard::new);
    public static final RegistryObject<Item> W2_REGLET = ITEMS.register("w2_reglet", EGOW2Reglet::new);
    public static final RegistryObject<Item> W2_RED_EYE = ITEMS.register("w2_red_eye", EGOW2RedEye::new);
    public static final RegistryObject<Item> W2_LANTERN = ITEMS.register("w2_lantern", EGOW2Lantern::new);
    public static final RegistryObject<Item> W1_PENITENCE = ITEMS.register("w1_penitence", EGOW1Penitence::new);

    //L社EGO武器 遠距離
    public static final RegistryObject<Item> W4_SOLEMN_LAMENT_L = ITEMS.register("w4_lament_l", EGOW4LamentL::new);
    public static final RegistryObject<Item> W4_SOLEMN_LAMENT_R = ITEMS.register("w4_lament_r",
            () -> new EGOW4LamentR(new Item.Properties().durability(3000)));
    public static final RegistryObject<Item> W4_MAGIC_BULLET = ITEMS.register("w4_magic_bullet",
            () -> new EGOW4MagicBullet(new Item.Properties().durability(3000)));
    public static final RegistryObject<Item> W4_HORNET = ITEMS.register("w4_hornet",
            () -> new EGOW4Hornet(new Item.Properties().durability(3000)));
    public static final RegistryObject<Item> W3_HARMONY = ITEMS.register("w3_harmony",
            () -> new EGOW3Harmony(new Item.Properties().durability(2000)));
    public static final RegistryObject<Item> W3_LAETITIA = ITEMS.register("w3_laetitia",
            () -> new EGOW3Laetitia(new Item.Properties().durability(2000)));
    public static final RegistryObject<Item> W2_BEAK = ITEMS.register("w2_beak",
            () -> new EGOW2Beak(new Item.Properties().durability(1000)));
    public static final RegistryObject<Item> W2_FOURTH_MATCH_FIRE = ITEMS.register("w2_match",
            () -> new EGOW2Match(new Item.Properties().durability(1000)));

    //L社EGO防具
    public static final RegistryObject<Item> A5_TWILIGHT_1 = ITEMS.register("s5_twilight_1",
            () -> new EGOS5Twilight(ArmorEquips.A5_TWILIGHT, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A5_TWILIGHT_2 = ITEMS.register("s5_twilight_2",
            () -> new EGOS5Twilight(ArmorEquips.A5_TWILIGHT, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A5_PARADISE_LOST_1 = ITEMS.register("s5_whitenight_1",
            () -> new EGOS5Whitenight(ArmorEquips.A5_PARADISE_LOST, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A5_PARADISE_LOST_2 = ITEMS.register("s5_whitenight_2",
            () -> new EGOS5Whitenight(ArmorEquips.A5_PARADISE_LOST, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A5_MIMICRY_2 = ITEMS.register("s5_mimicry_1",
            () -> new EGOS5Mimicry(ArmorEquips.A5_MIMICRY, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A5_MIMICRY_3 = ITEMS.register("s5_mimicry_2",
            () -> new EGOS5Mimicry(ArmorEquips.A5_MIMICRY, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A5_DA_CAPO_2 = ITEMS.register("s5_da_capo_1",
            () -> new EGOS5DaCapo(ArmorEquips.A5_DA_CAPO, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A5_DA_CAPO_3 = ITEMS.register("s5_da_capo_2",
            () -> new EGOS5DaCapo(ArmorEquips.A5_DA_CAPO, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A5_SMILE_2 = ITEMS.register("s5_smile_1",
            () -> new EGOS5Smile(ArmorEquips.A5_SMILE, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A5_SMILE_3 = ITEMS.register("s5_smile_2",
            () -> new EGOS5Smile(ArmorEquips.A5_SMILE, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A5_SOUND_OF_A_STAR_2 = ITEMS.register("s5_star_1",
            () -> new EGOS5Star(ArmorEquips.A5_SOUND_OF_A_STAR, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A5_SOUND_OF_A_STAR_3 = ITEMS.register("s5_star_2",
            () -> new EGOS5Star(ArmorEquips.A5_SOUND_OF_A_STAR, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A5_JUSTITIA_2 = ITEMS.register("s5_justitia_1",
            () -> new EGOS5Justitia(ArmorEquips.A5_JUSTITIA, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A5_JUSTITIA_3 = ITEMS.register("s5_justitia_2",
            () -> new EGOS5Justitia(ArmorEquips.A5_JUSTITIA, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A4_THE_SWORD_SHARPENED_WITH_TEARS_2 = ITEMS.register("s4_tears_1",
            () -> new EGOS4Tears(ArmorEquips.A4_THE_SWORD_SHARPENED_WITH_TEARS, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A4_THE_SWORD_SHARPENED_WITH_TEARS_3 = ITEMS.register("s4_tears_2",
            () -> new EGOS4Tears(ArmorEquips.A4_THE_SWORD_SHARPENED_WITH_TEARS, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A4_LAMP_2 = ITEMS.register("s4_lamp_1",
            () -> new EGOS4Lamp(ArmorEquips.A4_LAMP, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A4_LAMP_3 = ITEMS.register("s4_lamp_2",
            () -> new EGOS4Lamp(ArmorEquips.A4_LAMP, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A4_HORNET_2 = ITEMS.register("s4_hornet_1",
            () -> new EGOS4Hornet(ArmorEquips.A4_HORNET, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A4_HORNET_3 = ITEMS.register("s4_hornet_2",
            () -> new EGOS4Hornet(ArmorEquips.A4_HORNET, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A4_BLUE_SCAR_2 = ITEMS.register("s4_blue_scar_1",
            () -> new EGOS4BlueScar(ArmorEquips.A4_BLUE_SCAR, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A4_BLUE_SCAR_3 = ITEMS.register("s4_blue_scar_2",
            () -> new EGOS4BlueScar(ArmorEquips.A4_BLUE_SCAR, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A3_MAGIC_BULLET_2 = ITEMS.register("s3_magic_bullet_1",
            () -> new EGOS3MagicBullet(ArmorEquips.A3_MAGIC_BULLET, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A3_MAGIC_BULLET_3 = ITEMS.register("s3_magic_bullet_2",
            () -> new EGOS3MagicBullet(ArmorEquips.A3_MAGIC_BULLET, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A3_SOLEMN_LAMENT_1 = ITEMS.register("s3_lament_1",
            () -> new EGOS3Lament(ArmorEquips.A3_SOLEMN_LAMENT, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A3_SOLEMN_LAMENT_2 = ITEMS.register("s3_lament_2",
            () -> new EGOS3Lament(ArmorEquips.A3_SOLEMN_LAMENT, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A3_GALAXY_1 = ITEMS.register("s3_galaxy_1",
            () -> new EGOS3Galaxy(ArmorEquips.A3_GALAXY, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A3_GALAXY_2 = ITEMS.register("s3_galaxy_2",
            () -> new EGOS3Galaxy(ArmorEquips.A3_GALAXY, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A3_BLOOD_1 = ITEMS.register("s3_blood_1",
            () -> new EGOS3Blood(ArmorEquips.A3_BLOOD, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A3_BLOOD_2 = ITEMS.register("s3_blood_2",
            () -> new EGOS3Blood(ArmorEquips.A3_BLOOD, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A3_LOGGING_1 = ITEMS.register("s3_logging_1",
            () -> new EGOS3Logging(ArmorEquips.A3_LOGGING, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A3_LOGGING_2 = ITEMS.register("s3_logging_2",
            () -> new EGOS3Logging(ArmorEquips.A3_LOGGING, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A3_HARVEST_1 = ITEMS.register("s3_harvest_1",
            () -> new EGOS3Harvest(ArmorEquips.A3_HARVEST, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A3_HARVEST_2 = ITEMS.register("s3_harvest_2",
            () -> new EGOS3Harvest(ArmorEquips.A3_HARVEST, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A3_GRINDER_MK4_2 = ITEMS.register("s3_mk4_1",
            () -> new EGOS3MK4(ArmorEquips.A3_GRINDER_MK4, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A3_GRINDER_MK4_3 = ITEMS.register("s3_mk4_2",
            () -> new EGOS3MK4(ArmorEquips.A3_GRINDER_MK4, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A3_ICE_SHARD_2 = ITEMS.register("s3_ice_shard_1",
            () -> new EGOS3IceShard(ArmorEquips.A3_ICE_SHARD, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A3_ICE_SHARD_3 = ITEMS.register("s3_ice_shard_2",
            () -> new EGOS3IceShard(ArmorEquips.A3_ICE_SHARD, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A3_LAETITIA_2 = ITEMS.register("s3_laetitia_1",
            () -> new EGOS3Laetitia(ArmorEquips.A3_LAETITIA, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A3_LAETITIA_3 = ITEMS.register("s3_laetitia_2",
            () -> new EGOS3Laetitia(ArmorEquips.A3_LAETITIA, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A2_REGLET_2 = ITEMS.register("s2_reglet_1",
            () -> new EGOS2Reglet(ArmorEquips.A2_REGLET, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A2_REGLET_3 = ITEMS.register("s2_reglet_2",
            () -> new EGOS2Reglet(ArmorEquips.A2_REGLET, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A2_RED_EYE_2 = ITEMS.register("s2_red_eye_1",
            () -> new EGOS2RedEye(ArmorEquips.A2_RED_EYE, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A2_RED_EYE_3 = ITEMS.register("s2_red_eye_2",
            () -> new EGOS2RedEye(ArmorEquips.A2_RED_EYE, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A2_BEAK_1 = ITEMS.register("s2_beak_1",
            () -> new EGOS2Beak(ArmorEquips.A2_BEAK, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A2_BEAK_2 = ITEMS.register("s2_beak_2",
            () -> new EGOS2Beak(ArmorEquips.A2_BEAK, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A2_FOURTH_MATCH_FIRE_1 = ITEMS.register("s2_match_1",
            () -> new EGOS2Match(ArmorEquips.A2_FOURTH_MATCH_FIRE, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A2_FOURTH_MATCH_FIRE_2 = ITEMS.register("s2_match_2",
            () -> new EGOS2Match(ArmorEquips.A2_FOURTH_MATCH_FIRE, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A2_LANTERN_1 = ITEMS.register("s2_lantern_1",
            () -> new EGOS2Lantern(ArmorEquips.A2_LANTERN, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A2_LANTERN_2 = ITEMS.register("s2_lantern_2",
            () -> new EGOS2Lantern(ArmorEquips.A2_LANTERN, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A1_PENITENCE_2 = ITEMS.register("s1_penitence_1",
            () -> new EGOS1Penitence(ArmorEquips.A1_PENITENCE, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A1_PENITENCE_3 = ITEMS.register("s1_penitence_2",
            () -> new EGOS1Penitence(ArmorEquips.A1_PENITENCE, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    //フィクサー武器 近距離
    public static final RegistryObject<Item> G_MIMICRY = ITEMS.register("fixer_kali_mimicry", WeaponKaliMimicry::new);
    public static final RegistryObject<Item> EX_DURANDAL = ITEMS.register("fixer_roland_durandal", WeaponRolandDurandal::new);
    public static final RegistryObject<Item> EX_ARGALIA = ITEMS.register("fixer_argalia", WeaponArgalia::new);
    public static final RegistryObject<Item> ASC2_SOUTH_W1 = ITEMS.register("asc2_south_weapon1", Asc2SouthWeapon1::new);
    public static final RegistryObject<Item> ASC2_SOUTH_W2 = ITEMS.register("asc2_south_weapon2", Asc2SouthWeapon2::new);
    public static final RegistryObject<Item> ASC2_WALTER = ITEMS.register("asc2_south_walter", Asc2Walter::new);
    public static final RegistryObject<Item> ASC2_WEST_WEAPON = ITEMS.register("asc2_west_weapon", Asc2WestWeapon::new);

    //フィクサー武器 遠距離
    public static final RegistryObject<Item> WEAPON_ROLAND_REVOLVER = ITEMS.register("fixer_roland_lar",
            () -> new WeaponRolandRevolver(new Item.Properties().durability(2000)));
    public static final RegistryObject<Item> WEAPON_ROLAND_SHOTGUN = ITEMS.register("fixer_roland_lasg",
            () -> new WeaponRolandShotgun(new Item.Properties().durability(2000)));

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

    public static final RegistryObject<Item> WCORP_WEAPON_1 = ITEMS.register("wcorp_w1", WCorpWeapon1::new);
    public static final RegistryObject<Item> WCORP_WEAPON_2 = ITEMS.register("wcorp_w2", WCorpWeapon2::new);
    public static final RegistryObject<Item> WCORP_WEAPON_3 = ITEMS.register("wcorp_w3", WCorpWeapon3::new);

    //R社装備
    public static final RegistryObject<Item> RCORP_RABBIT_RIFLE = ITEMS.register("rcorp_rabbit_rifle",
            () -> new RCorpRabbitRifle(new Item.Properties().durability(2000)));
    public static final RegistryObject<Item> RCORP_RABBIT_KNIFE = ITEMS.register("rcorp_rabbit_knife", RCorpRabbitCombatKnife::new);

    //K社装備
    public static final RegistryObject<Item> KCORP_WEAPON_1 = ITEMS.register("kcorp_w1", KCorpWeapon1::new);
    public static final RegistryObject<Item> KCORP_WEAPON_2 = ITEMS.register("kcorp_w2", KCorpWeapon2::new);
    public static final RegistryObject<Item> KCORP_WEAPON_3 = ITEMS.register("kcorp_w3", KCorpWeapon3::new);
}
