package net.lobotomy_corporation_mod.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class Config {
    public static final ForgeConfigSpec CLIENT_CONFIG;
    public static final ForgeConfigSpec.BooleanValue ALLOW_TERRAIN_DAMAGE;
    public static final ForgeConfigSpec.BooleanValue ALLOW_FRIENDLY_FIRE;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.comment("EGO Weapons Config").push("weapons");

        // 射撃武器による地形破壊 ON/OFF
        ALLOW_TERRAIN_DAMAGE = builder
                .comment("Enable terrain destruction from projectile weapons (default: false)")
                .define("allowTerrainDamage", true);

        // 武器によるフレンドリーファイア ON/OFF
        ALLOW_FRIENDLY_FIRE = builder
                .comment("Allow friendly fire with weapons (default: false)")
                .define("allowFriendlyFire", false);

        builder.pop();
        CLIENT_CONFIG = builder.build();
    }
}