package net.pm_equips;

import net.pm_equips.entity.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityInit {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, PMEquipsMain.MOD_ID);

    public static final RegistryObject<EntityType<EGOStar>> W5_SOUND_OF_A_STAR_PROJECTILE =
            ENTITY_TYPES.register("ego_sound_of_a_star_projectile",
                    () -> EntityType.Builder.<EGOStar>of(EGOStar::new, MobCategory.MISC)
                            .sized(0.25F, 0.25F)
                            .clientTrackingRange(4)
                            .updateInterval(10)
                            .build("ego_sound_of_a_star_projectile"));

    public static final RegistryObject<EntityType<EGOMagic>> MAGIC_BULLET =
            ENTITY_TYPES.register("magic_bullet", () ->
                    EntityType.Builder.<EGOMagic>of(
                                    EGOMagic::new,
                                    MobCategory.MISC
                            )
                            .sized(0.3F, 0.3F)
                            .clientTrackingRange(64)
                            .updateInterval(1)
                            .build("magic_bullet"));

    public static final RegistryObject<EntityType<PBullet>> BULLET =
            ENTITY_TYPES.register("bullet", () ->
                    EntityType.Builder.<PBullet>of(
                                    PBullet::new,
                                    MobCategory.MISC
                            )
                            .sized(0.3F, 0.3F)
                            .clientTrackingRange(64)
                            .updateInterval(1)
                            .build("bullet"));

    public static final RegistryObject<EntityType<PBulletLARV>> BULLET_LARV =
            ENTITY_TYPES.register("bullet_larv", () ->
                    EntityType.Builder.<PBulletLARV>of(
                                    PBulletLARV::new,
                                    MobCategory.MISC
                            )
                            .sized(0.25F, 0.25F)
                            .clientTrackingRange(64)
                            .updateInterval(1)
                            .build("bullet_larv"));

    public static final RegistryObject<EntityType<PBulletLASG>> BULLET_LASG =
            ENTITY_TYPES.register("bullet_lasg", () ->
                    EntityType.Builder.<PBulletLASG>of(
                                    PBulletLASG::new,
                                    MobCategory.MISC
                            )
                            .sized(0.25F, 0.25F)
                            .clientTrackingRange(64)
                            .updateInterval(1)
                            .build("bullet_larv"));

    public static final RegistryObject<EntityType<PBulletExp>> BULLET_EX =
            ENTITY_TYPES.register("bullet_ex", () ->
                    EntityType.Builder.<PBulletExp>of(
                            PBulletExp::new,
                            MobCategory.MISC
                            )
                            .sized(1.5F, 1.5F)
                            .clientTrackingRange(64)
                            .updateInterval(1)
                            .build("bullet_ex"));

}
