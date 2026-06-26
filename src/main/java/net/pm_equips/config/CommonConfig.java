package net.pm_equips.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class CommonConfig {

    public static final ForgeConfigSpec COMMON_CONFIG;

    public static final ForgeConfigSpec.BooleanValue ALLOW_TERRAIN_DAMAGE;

    public static final ForgeConfigSpec.BooleanValue ALLOW_FRIENDLY_FIRE;

    static {

        ForgeConfigSpec.Builder builder =
                new ForgeConfigSpec.Builder();

        builder.comment("PM Equips Config")
                .push("weapons");

        ALLOW_TERRAIN_DAMAGE =
                builder
                        .comment("Enable terrain destruction from projectile weapons")
                        .define(
                                "allowTerrainDamage",
                                true
                        );

        ALLOW_FRIENDLY_FIRE =
                builder
                        .comment("Allow friendly fire")
                        .define(
                                "allowFriendlyFire",
                                false
                        );

        builder.pop();

        COMMON_CONFIG =
                builder.build();
    }
}