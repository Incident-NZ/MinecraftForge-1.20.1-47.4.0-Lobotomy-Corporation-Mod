package net.lobotomy_corporation_mod;

import net.lobotomy_corporation_mod.items.*;
import net.lobotomy_corporation_mod.items.materials.EGOArmor;
import net.lobotomy_corporation_mod.items.g1penitence;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Lobotomy_corporation_mod.MOD_ID);

    //Item
    public static final RegistryObject<Item> TASK_TABLET = ITEMS.register("tool_task_tablet",
            () -> new Item(new Item.Properties().stacksTo(1)));

    //Ammo
    public static final RegistryObject<Item> MAGIC_BULLET_AMMO = ITEMS.register("weapon_bullet_magic",
            () -> new MagicBulletItem(new ResourceLocation("lobotomy_corporation_mod", "magic_bullet")));
    public static final RegistryObject<Item> RIFLE_BULLET_AMMO = ITEMS.register("weapon_bullet_rifle",
            () -> new MagicBulletItem(new ResourceLocation("lobotomy_corporation_mod", "rifle_bullet")));
    public static final RegistryObject<Item> PISTOL_BULLET_AMMO = ITEMS.register("weapon_bullet_pistol",
            () -> new MagicBulletItem(new ResourceLocation("lobotomy_corporation_mod", "pistol_bullet")));

    //EGO Weapons
    public static final RegistryObject<Item> W5_TWILIGHT = ITEMS.register("w5_twilight", W5Twilight::new);
    public static final RegistryObject<Item> W5_PARADISE_LOST = ITEMS.register("w5_whitenight", W5WhiteNight::new);
    public static final RegistryObject<Item> W5_MIMICRY = ITEMS.register("w5_mimicry", W5Mimicry::new);
    public static final RegistryObject<Item> W5_DA_CAPO = ITEMS.register("w5_da_capo", W5DaCapo::new);
    public static final RegistryObject<Item> W5_SMILE = ITEMS.register("w5_smile", W5Smile::new);
    public static final RegistryObject<Item> W5_SOUND_OF_A_STAR = ITEMS.register("w5_star", W5Star::new);
    public static final RegistryObject<Item> W5_JUSTITIA = ITEMS.register("w5_justitia", W5Justitia::new);
    public static final RegistryObject<Item> W4_THE_SWORD_SHARPENED_WITH_TEARS = ITEMS.register("w4_tears", W4Tears::new);
    public static final RegistryObject<Item> W4_HORNET = ITEMS.register("w4_hornet", W4Hornet::new);
    public static final RegistryObject<Item> W4_LAMP = ITEMS.register("w4_lamp", W4Lamp::new);
    public static final RegistryObject<Item> W4_BLUE_SCAR = ITEMS.register("w4_blue_scar", W4BlueScar::new);
    public static final RegistryObject<Item> W4_SOLEMN_LAMENT_L = ITEMS.register("w4_lament_l", W4LamentL::new);
    public static final RegistryObject<Item> W4_SOLEMN_LAMENT_R = ITEMS.register("w4_lament_r", W4LamentR::new);
    public static final RegistryObject<Item> W3_BLOOD = ITEMS.register("w3_blood", W3Blood::new);
    public static final RegistryObject<Item> W3_HARVEST = ITEMS.register("w3_harvest", W3Harvest::new);
    public static final RegistryObject<Item> W3_LOGGING = ITEMS.register("w3_logging", W3Logging::new);
    public static final RegistryObject<Item> W3_GALAXY = ITEMS.register("w3_galaxy", W3Galaxy::new);
    public static final RegistryObject<Item> W3_GRINDER_MK4 = ITEMS.register("w3_mk4", W3MK4::new);
    public static final RegistryObject<Item> W3_HARMONY = ITEMS.register("w3_harmony", W3Harmony::new);
    public static final RegistryObject<Item> W3_LAETITIA = ITEMS.register("w3_laetitia", W3Laetitia::new);
    public static final RegistryObject<Item> W3_ICE_SHARD = ITEMS.register("w3_ice_shard", W3IceShard::new);
    public static final RegistryObject<Item> W2_REGLET = ITEMS.register("w2_reglet", W2Reglet::new);
    public static final RegistryObject<Item> W2_RED_EYE = ITEMS.register("w2_red_eye", W2RedEye::new);
    public static final RegistryObject<Item> W2_LANTERN = ITEMS.register("w2_lantern", W2Lantern::new);
    public static final RegistryObject<Item> W2_BEAK = ITEMS.register("w2_beak", W2Beak::new);
    public static final RegistryObject<Item> W2_FOURTH_MATCH_FIRE = ITEMS.register("w2_match", W2Match::new);
    public static final RegistryObject<Item> W1_PENITENCE = ITEMS.register("w1_penitence", W1Penitence::new);
    public static final RegistryObject<Item> G_MIMICRY = ITEMS.register("ex_mimicry", WeaponKaliMimicry::new);
    public static final RegistryObject<Item> EX_DURANDAL = ITEMS.register("ex_durandal", WeaponRolandDurandal::new);
    public static final RegistryObject<Item> EX_ARGALIA = ITEMS.register("ex_argalia", WeaponArgalia::new);

    //EGO Guns
    public static final RegistryObject<Item> W4_MAGIC_BULLET = ITEMS.register("w4_magic_bullet",
            () -> new W4MagicBullet(
                    new ResourceLocation("lobotomy_corporation_mod", "magic_bullet"),
                    24.0F, 3.0F));

    //EGO Suit
    public static final RegistryObject<Item> A5_TWILIGHT_1 = ITEMS.register("s5_twilight_1",
            () -> new s5twilight(EGOArmor.A5_TWILIGHT, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A5_TWILIGHT_2 = ITEMS.register("s5_twilight_2",
            () -> new s5twilight(EGOArmor.A5_TWILIGHT, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A5_PARADISE_LOST_1 = ITEMS.register("s5_whitenight_1",
            () -> new s5whitenight(EGOArmor.A5_PARADISE_LOST, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A5_PARADISE_LOST_2 = ITEMS.register("s5_whitenight_2",
            () -> new s5whitenight(EGOArmor.A5_PARADISE_LOST, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A5_MIMICRY_2 = ITEMS.register("s5_mimicry_1",
            () -> new s5mimcry(EGOArmor.A5_MIMICRY, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A5_MIMICRY_3 = ITEMS.register("s5_mimicry_2",
            () -> new s5mimcry(EGOArmor.A5_MIMICRY, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A5_DA_CAPO_2 = ITEMS.register("s5_da_capo_1",
            () -> new s5dacapo(EGOArmor.A5_DA_CAPO, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A5_DA_CAPO_3 = ITEMS.register("s5_da_capo_2",
            () -> new s5dacapo(EGOArmor.A5_DA_CAPO, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A5_SMILE_2 = ITEMS.register("s5_smile_1",
            () -> new s5smile(EGOArmor.A5_SMILE, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A5_SMILE_3 = ITEMS.register("s5_smile_2",
            () -> new s5smile(EGOArmor.A5_SMILE, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A5_SOUND_OF_A_STAR_2 = ITEMS.register("s5_star_1",
            () -> new s5star(EGOArmor.A5_SOUND_OF_A_STAR, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A5_SOUND_OF_A_STAR_3 = ITEMS.register("s5_star_2",
            () -> new s5star(EGOArmor.A5_SOUND_OF_A_STAR, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A5_JUSTITIA_2 = ITEMS.register("s5_justitia_1",
            () -> new s5justitia(EGOArmor.A5_JUSTITIA, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A5_JUSTITIA_3 = ITEMS.register("s5_justitia_2",
            () -> new s5justitia(EGOArmor.A5_JUSTITIA, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A4_THE_SWORD_SHARPENED_WITH_TEARS_2 = ITEMS.register("s4_tears_1",
            () -> new s4tears(EGOArmor.A4_THE_SWORD_SHARPENED_WITH_TEARS, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A4_THE_SWORD_SHARPENED_WITH_TEARS_3 = ITEMS.register("s4_tears_2",
            () -> new s4tears(EGOArmor.A4_THE_SWORD_SHARPENED_WITH_TEARS, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A4_LAMP_2 = ITEMS.register("s4_lamp_1",
            () -> new s4lamp(EGOArmor.A4_LAMP, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A4_LAMP_3 = ITEMS.register("s4_lamp_2",
            () -> new s4lamp(EGOArmor.A4_LAMP, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A4_HORNET_2 = ITEMS.register("s4_hornet_1",
            () -> new s4hornet(EGOArmor.A4_HORNET, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A4_HORNET_3 = ITEMS.register("s4_hornet_2",
            () -> new s4hornet(EGOArmor.A4_HORNET, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A4_BLUE_SCAR_2 = ITEMS.register("s4_blue_scar_1",
            () -> new s4bluescar(EGOArmor.A4_BLUE_SCAR, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A4_BLUE_SCAR_3 = ITEMS.register("s4_blue_scar_2",
            () -> new s4bluescar(EGOArmor.A4_BLUE_SCAR, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A3_MAGIC_BULLET_2 = ITEMS.register("s3_magic_bullet_1",
            () -> new s3magicbullet(EGOArmor.A3_MAGIC_BULLET, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A3_MAGIC_BULLET_3 = ITEMS.register("s3_magic_bullet_2",
            () -> new s3magicbullet(EGOArmor.A3_MAGIC_BULLET, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A3_SOLEMN_LAMENT_1 = ITEMS.register("s3_lament_1",
            () -> new s3magicbullet(EGOArmor.A3_SOLEMN_LAMENT, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A3_SOLEMN_LAMENT_2 = ITEMS.register("s3_lament_2",
            () -> new s3magicbullet(EGOArmor.A3_SOLEMN_LAMENT, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A3_GALAXY_1 = ITEMS.register("s3_galaxy_1",
            () -> new s3magicbullet(EGOArmor.A3_GALAXY, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A3_GALAXY_2 = ITEMS.register("s3_galaxy_2",
            () -> new s3magicbullet(EGOArmor.A3_GALAXY, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A3_BLOOD_1 = ITEMS.register("s3_blood_1",
            () -> new s3magicbullet(EGOArmor.A3_BLOOD, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A3_BLOOD_2 = ITEMS.register("s3_blood_2",
            () -> new s3magicbullet(EGOArmor.A3_BLOOD, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A3_LOGGING_1 = ITEMS.register("s3_logging_1",
            () -> new s3magicbullet(EGOArmor.A3_LOGGING, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A3_LOGGING_2 = ITEMS.register("s3_logging_2",
            () -> new s3magicbullet(EGOArmor.A3_LOGGING, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A3_HARVEST_1 = ITEMS.register("s3_harvest_1",
            () -> new s3magicbullet(EGOArmor.A3_HARVEST, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A3_HARVEST_2 = ITEMS.register("s3_harvest_2",
            () -> new s3magicbullet(EGOArmor.A3_HARVEST, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A3_GRINDER_MK4_2 = ITEMS.register("s3_mk4_1",
            () -> new s3magicbullet(EGOArmor.A3_GRINDER_MK4, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A3_GRINDER_MK4_3 = ITEMS.register("s3_mk4_2",
            () -> new s3magicbullet(EGOArmor.A3_GRINDER_MK4, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A3_ICE_SHARD_2 = ITEMS.register("s3_ice_shard_1",
            () -> new s3iceshard(EGOArmor.A3_ICE_SHARD, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A3_ICE_SHARD_3 = ITEMS.register("s3_ice_shard_2",
            () -> new s3iceshard(EGOArmor.A3_ICE_SHARD, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A3_LAETITIA_2 = ITEMS.register("s3_laetitia_1",
            () -> new s3laetitia(EGOArmor.A3_LAETITIA, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A3_LAETITIA_3 = ITEMS.register("s3_laetitia_2",
            () -> new s3laetitia(EGOArmor.A3_LAETITIA, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A2_REGLET_2 = ITEMS.register("s2_reglet_1",
            () -> new s2reglet(EGOArmor.A2_REGLET, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A2_REGLET_3 = ITEMS.register("s2_reglet_2",
            () -> new s2reglet(EGOArmor.A2_REGLET, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A2_RED_EYE_2 = ITEMS.register("s2_red_eye_1",
            () -> new s2redeye(EGOArmor.A2_RED_EYE, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A2_RED_EYE_3 = ITEMS.register("s2_red_eye_2",
            () -> new s2redeye(EGOArmor.A2_RED_EYE, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A2_BEAK_1 = ITEMS.register("s2_beak_1",
            () -> new s2beak(EGOArmor.A2_BEAK, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A2_BEAK_2 = ITEMS.register("s2_beak_2",
            () -> new s2beak(EGOArmor.A2_BEAK, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A2_FOURTH_MATCH_FIRE_1 = ITEMS.register("s2_match_1",
            () -> new s2match(EGOArmor.A2_FOURTH_MATCH_FIRE, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A2_FOURTH_MATCH_FIRE_2 = ITEMS.register("s2_match_2",
            () -> new s2match(EGOArmor.A2_FOURTH_MATCH_FIRE, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A2_LANTERN_1 = ITEMS.register("s2_lantern_1",
            () -> new s2lantern(EGOArmor.A2_LANTERN, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A2_LANTERN_2 = ITEMS.register("s2_lantern_2",
            () -> new s2lantern(EGOArmor.A2_LANTERN, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A1_PENITENCE_2 = ITEMS.register("s1_penitence_1",
            () -> new s1penitence(EGOArmor.A1_PENITENCE, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> A1_PENITENCE_3 = ITEMS.register("s1_penitence_2",
            () -> new s1penitence(EGOArmor.A1_PENITENCE, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));

    //ego_gift
    public static final RegistryObject<Item> EGO_GIFT_PENITENCE = ITEMS.register("g1_penitence",
            () -> new g1penitence(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> EGO_GIFT_BEAK = ITEMS.register("g2_beak",
            () -> new g2beak(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> EGO_GIFT_LANTERN = ITEMS.register("g2_lantern",
            () -> new g2lantern(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> EGO_GIFT_MATCH = ITEMS.register("g2_match",
            () -> new g2match(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> EGO_GIFT_RED_EYE = ITEMS.register("g2_red_eye",
            () -> new g2redeye(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> EGO_GIFT_REGLET = ITEMS.register("g2_reglet",
            () -> new g2reglet(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> EGO_GIFT_ICE_SHARD = ITEMS.register("g3_ice_shard",
            () -> new g3iceshard(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> EGO_GIFT_LAETITIA = ITEMS.register("g3_laetitia",
            () -> new g3laetitia(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> EGO_GIFT_MK4 = ITEMS.register("g3_mk4",
            () -> new g3mk4(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> EGO_GIFT_LAMENT = ITEMS.register("g3_lament",
            () -> new g3lament(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> EGO_GIFT_BLOOD = ITEMS.register("g3_blood",
            () -> new g3blood(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> EGO_GIFT_GALAXY = ITEMS.register("g3_galaxy",
            () -> new g3galaxy(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> EGO_GIFT_HARMONY = ITEMS.register("g3_harmony",
            () -> new g3harmony(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> EGO_GIFT_LOGGING = ITEMS.register("g3_logging",
            () -> new g3logging(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> EGO_GIFT_MAGIC_BULLET = ITEMS.register("g3_magic_bullet",
            () -> new g3magicbullet(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> EGO_GIFT_BLUE_SCAR = ITEMS.register("g4_blue_scar",
            () -> new g4bluescar(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> EGO_GIFT_HORNET = ITEMS.register("g4_hornet",
            () -> new g4hornet(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> EGO_GIFT_LAMP = ITEMS.register("g4_lamp",
            () -> new g4lamp(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> EGO_GIFT_TEARS = ITEMS.register("g4_tears",
            () -> new g4tears(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> EGO_GIFT_DA_CAPO = ITEMS.register("g5_da_capo",
            () -> new g5dacapo(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> EGO_GIFT_JUSTITIA = ITEMS.register("g5_justitia",
            () -> new g5justitia(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> EGO_GIFT_MIMICRY = ITEMS.register("g5_mimicry",
            () -> new g5mimicry(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> EGO_GIFT_STAR = ITEMS.register("g5_star",
            () -> new g5star(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> EGO_GIFT_SMILE = ITEMS.register("g5_smile",
            () -> new g5smile(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> EGO_GIFT_WHITENIGHT = ITEMS.register("g5_whitenight",
            () -> new g5whitenight(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> EGO_GIFT_TWILIGHT = ITEMS.register("g5_twilight",
            () -> new g5twilight(new Item.Properties().stacksTo(1)));
}
