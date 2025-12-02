package net.lobotomy_corporation_mod.items.materials;

import net.lobotomy_corporation_mod.BlockInit;
import net.lobotomy_corporation_mod.Lobotomy_corporation_mod;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum EGOArmor implements ArmorMaterial {
    A5_TWILIGHT("s5_twilight", 4000, new int[]{0, 60, 60, 0}, 0,
            SoundEvents.ARMOR_EQUIP_GENERIC, 20.0f, 2.0f, () -> Ingredient.of(BlockInit.BlockItems.ALEPH_PE_BOX.get())),

    A5_PARADISE_LOST("s5_white_night", 4000, new int[]{0, 60, 60, 0}, 0,
            SoundEvents.ARMOR_EQUIP_GENERIC, 20.0f, 2.0f, () -> Ingredient.of(BlockInit.BlockItems.ALEPH_PE_BOX.get())),

    A5_MIMICRY("s5_mimicry", 4000, new int[]{0, 50, 50, 0}, 0,
            SoundEvents.ARMOR_EQUIP_GENERIC, 20.0f, 2.0f, () -> Ingredient.of(BlockInit.BlockItems.ALEPH_PE_BOX.get())),

    A5_SMILE("s5_smile", 4000, new int[]{0, 50, 50, 0}, 0,
            SoundEvents.ARMOR_EQUIP_GENERIC, 20.0f, 2.0f, () -> Ingredient.of(BlockInit.BlockItems.ALEPH_PE_BOX.get())),

    A5_SOUND_OF_A_STAR("s5_star", 14000, new int[]{0, 50, 50, 0}, 0,
            SoundEvents.ARMOR_EQUIP_GENERIC, 20.0f, 2.0f, () -> Ingredient.of(BlockInit.BlockItems.ALEPH_PE_BOX.get())),

    A5_DA_CAPO("s5_da_capo", 4000, new int[]{0, 50, 50, 0}, 0,
            SoundEvents.ARMOR_EQUIP_GENERIC, 20.0f, 2.0f, () -> Ingredient.of(BlockInit.BlockItems.ALEPH_PE_BOX.get())),

    A5_JUSTITIA("s5_justitia", 4000, new int[]{0, 50, 50, 0}, 0,
            SoundEvents.ARMOR_EQUIP_GENERIC, 20.0f, 2.0f, () -> Ingredient.of(BlockInit.BlockItems.ALEPH_PE_BOX.get())),

    A4_THE_SWORD_SHARPENED_WITH_TEARS("s4_tears", 3000, new int[]{0, 40, 40, 0}, 0,
            SoundEvents.ARMOR_EQUIP_GENERIC, 15.0f, 1.5f, () -> Ingredient.of(BlockInit.BlockItems.WAW_PE_BOX.get())),

    A4_HORNET("s4_hornet", 3000, new int[]{0, 40, 40, 0}, 0,
            SoundEvents.ARMOR_EQUIP_GENERIC, 15.0f, 1.5f, () -> Ingredient.of(BlockInit.BlockItems.WAW_PE_BOX.get())),

    A4_LAMP("s4_lamp", 3000, new int[]{0, 40, 40, 0}, 0,
            SoundEvents.ARMOR_EQUIP_GENERIC, 15.0f, 1.5f, () -> Ingredient.of(BlockInit.BlockItems.WAW_PE_BOX.get())),

    A4_BLUE_SCAR("s4_blue_scar", 3000, new int[]{0, 40, 40, 0}, 0,
            SoundEvents.ARMOR_EQUIP_GENERIC, 15.0f, 1.5f, () -> Ingredient.of(BlockInit.BlockItems.WAW_PE_BOX.get())),

    A3_MAGIC_BULLET("s3_magic_bullet", 2000, new int[]{0, 30, 30, 0}, 0,
            SoundEvents.ARMOR_EQUIP_GENERIC, 12.0f, 1.2f, () -> Ingredient.of(BlockInit.BlockItems.HE_PE_BOX.get())),

    A3_ICE_SHARD("s3_ice_shard", 2000, new int[]{0, 30, 30, 0}, 0,
            SoundEvents.ARMOR_EQUIP_GENERIC, 10.0f, 1.2f, () -> Ingredient.of(BlockInit.BlockItems.HE_PE_BOX.get())),

    A3_LAETITIA("s3_laetitia", 2000, new int[]{0, 30, 30, 0}, 0,
            SoundEvents.ARMOR_EQUIP_GENERIC, 10.0f, 1.2f, () -> Ingredient.of(BlockInit.BlockItems.HE_PE_BOX.get())),

    A3_BLOOD("a3_blood", 2000, new int[]{0, 30, 30, 0}, 0,
            SoundEvents.ARMOR_EQUIP_GENERIC, 10.0f, 1.2f, () -> Ingredient.of(BlockInit.BlockItems.HE_PE_BOX.get())),

    A3_SOLEMN_LAMENT("a3_solemn_lament", 2000, new int[]{0, 30, 30, 0}, 0,
            SoundEvents.ARMOR_EQUIP_GENERIC, 10.0f, 1.2f, () -> Ingredient.of(BlockInit.BlockItems.HE_PE_BOX.get())),

    A3_LOGGING("a3_logging", 2000, new int[]{0, 30, 30, 0}, 0,
            SoundEvents.ARMOR_EQUIP_GENERIC, 10.0f, 1.2f, () -> Ingredient.of(BlockInit.BlockItems.HE_PE_BOX.get())),

    A3_GRINDER_MK4("a3_grinder_mk4", 2000, new int[]{0, 30, 30, 0}, 0,
            SoundEvents.ARMOR_EQUIP_GENERIC, 10.0f, 1.2f, () -> Ingredient.of(BlockInit.BlockItems.HE_PE_BOX.get())),

    A3_GALAXY("a3_galaxy", 2000, new int[]{0, 30, 30, 0}, 0,
            SoundEvents.ARMOR_EQUIP_GENERIC, 10.0f, 1.2f, () -> Ingredient.of(BlockInit.BlockItems.HE_PE_BOX.get())),

    A3_HARVEST("a3_harvest", 2000, new int[]{0, 30, 30, 0}, 0,
            SoundEvents.ARMOR_EQUIP_GENERIC, 10.0f, 1.2f, () -> Ingredient.of(BlockInit.BlockItems.HE_PE_BOX.get())),

    A2_FOURTH_MATCH_FIRE("s2_match", 1000, new int[]{0, 20, 20, 0}, 0,
            SoundEvents.ARMOR_EQUIP_GENERIC, 8.0f, 1.0f, () -> Ingredient.of(BlockInit.BlockItems.TETH_PE_BOX.get())),

    A2_BEAK("s2_beak", 1000, new int[]{0, 20, 20, 0}, 0,
            SoundEvents.ARMOR_EQUIP_GENERIC, 8.0f, 1.0f, () -> Ingredient.of(BlockInit.BlockItems.TETH_PE_BOX.get())),

    A2_LANTERN("s2_lantern", 1000, new int[]{0, 20, 20, 0}, 0,
            SoundEvents.ARMOR_EQUIP_GENERIC, 8.0f, 1.0f, () -> Ingredient.of(BlockInit.BlockItems.TETH_PE_BOX.get())),

    A2_RED_EYE("s2_red_eye", 1000, new int[]{0, 20, 20, 0}, 0,
            SoundEvents.ARMOR_EQUIP_GENERIC, 8.0f, 1.0f, () -> Ingredient.of(BlockInit.BlockItems.TETH_PE_BOX.get())),

    A2_REGLET("s2_reglet", 1000, new int[]{0, 20, 20, 0}, 0,
            SoundEvents.ARMOR_EQUIP_GENERIC, 8.0f, 1.0f, () -> Ingredient.of(BlockInit.BlockItems.TETH_PE_BOX.get())),

    A1_PENITENCE("s1_penitence", 800, new int[]{0, 20, 20, 0}, 0,
            SoundEvents.ARMOR_EQUIP_GENERIC, 8.0f, 1.0f, () -> Ingredient.of(BlockInit.BlockItems.ZAYIN_PE_BOX.get()));

    private final String name;
    private final int durabilityMultiplier;
    private final int[] protectionAmounts;
    private final int enchantmentValue;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float KnockBackResistance;
    private final Supplier<Ingredient> repairIngredient;

    private static final int[] BASE_DURABILITY = {0, 1, 1, 0};

    EGOArmor(String name, int durabilityMultiplier, int[] protectionAmounts, int enchantmentValue, SoundEvent equipSound, float toughness, float knockBackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionAmounts = protectionAmounts;
        this.enchantmentValue = enchantmentValue;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.KnockBackResistance = knockBackResistance;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type ptype) {
        return BASE_DURABILITY[ptype.ordinal()] * this.durabilityMultiplier;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type ptype) {
        return this.protectionAmounts[ptype.ordinal()];
    }

    @Override
    public int getEnchantmentValue() {
        return enchantmentValue;
    }

    @Override
    public SoundEvent getEquipSound() {
        return equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Override
    public String getName() {
        return Lobotomy_corporation_mod.MOD_ID + ":" + this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.KnockBackResistance;
    }
}
