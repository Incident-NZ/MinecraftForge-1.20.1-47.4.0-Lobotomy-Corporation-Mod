package net.lobotomy_corporation_mod;

import net.lobotomy_corporation_mod.entity.BulletEntity;
import net.lobotomy_corporation_mod.entity.MagicBulletEntity;
import net.lobotomy_corporation_mod.entity.W5StarProjectile;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityInit {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Lobotomy_corporation_mod.MOD_ID);

    public static final RegistryObject<EntityType<W5StarProjectile>> W5_SOUND_OF_A_STAR_PROJECTILE =
            ENTITY_TYPES.register("ego_sound_of_a_star_projectile",
                    () -> EntityType.Builder.<W5StarProjectile>of(W5StarProjectile::new, MobCategory.MISC)
                            .sized(0.25F, 0.25F)
                            .clientTrackingRange(4)
                            .updateInterval(10)
                            .build("ego_sound_of_a_star_projectile"));

    public static final RegistryObject<EntityType<MagicBulletEntity>> MAGIC_BULLET =
            ENTITY_TYPES.register("magic_bullet", () ->
                    EntityType.Builder.<MagicBulletEntity>of(
                                    MagicBulletEntity::new,
                                    MobCategory.MISC
                            )
                            .sized(0.3F, 0.3F)
                            .clientTrackingRange(64)
                            .updateInterval(1)
                            .build("magic_bullet"));

    public static final RegistryObject<EntityType<BulletEntity>> BULLET =
            ENTITY_TYPES.register("bullet", () ->
                    EntityType.Builder.<BulletEntity>of(
                                    BulletEntity::new,
                                    MobCategory.MISC
                            )
                            .sized(0.3F, 0.3F)
                            .clientTrackingRange(64)
                            .updateInterval(1)
                            .build("bullet"));

}
