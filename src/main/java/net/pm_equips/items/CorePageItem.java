package net.pm_equips.items;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.pm_equips.compat.Curios;
import net.pm_equips.items.materials.ArmorEquips;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.Animation;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.UUID;

public class CorePageItem extends ArmorItem implements GeoItem, ICurioItem {
    private final ArmorEquips material;
    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public CorePageItem(ArmorMaterial material, ArmorItem.Type type, Properties properties) {
        super(material, type, properties);
        this.material = (ArmorEquips) material;
    }

    public ResourceLocation getModelResource() {
        return new ResourceLocation("pm_equips", isTypeOneModel() ? "geo/ego_armor_type1.geo.json" :
                material == ArmorEquips.WCORP_ARMOR || material == ArmorEquips.WCORP_ARMOR_ACE
                        ? "geo/wcorp_armor.geo.json" : "geo/ego_armor_type2.geo.json");
    }

    public ResourceLocation getTextureResource() {
        return new ResourceLocation("pm_equips", "textures/armor/" + switch (material) {
            case A5_PARADISE_LOST -> "ego_s5_whitenight.png";
            case A4_THE_SWORD_SHARPENED_WITH_TEARS -> "ego_s4_tears.png";
            case A4_CRIMSON_SCAR -> "ego_s4_crimsom_scar.png";
            case A2_FOURTH_MATCH_FIRE -> "ego_s2_match.png";
            case A3_SOLEMN_LAMENT -> "ego_s3_lament.png";
            case A3_GRINDER_MK4 -> "ego_s3_mk4.png";
            case A3_BLOOD -> "ego_s3_blood.png";
            case A3_LOGGING -> "ego_s3_logging.png";
            case A3_HARVEST -> "ego_s3_harvest.png";
            case A3_GALAXY -> "ego_s3_galaxy.png";
            case WCORP_ARMOR -> "wcorp_armor.png";
            case WCORP_ARMOR_ACE -> "wcorp_armor_adept.png";
            default -> "ego_" + material.getName().substring(material.getName().indexOf(':') + 1) + ".png";
        });
    }

    public ResourceLocation getAnimationResource() {
        return new ResourceLocation("pm_equips", material == ArmorEquips.WCORP_ARMOR || material == ArmorEquips.WCORP_ARMOR_ACE
                ? "animations/wcorp.animation.json" : "animations/ego_armor.animation.json");
    }

    private boolean isTypeOneModel() {
        return switch (material) {
            case A5_TWILIGHT, A5_SOUND_OF_A_STAR, A5_JUSTITIA, A4_THE_SWORD_SHARPENED_WITH_TEARS,
                 A4_HORNET, A4_LAMP, A4_AROMA, A4_STEM, A4_SWAN, A4_CRIMSON_SCAR, A3_MAGIC_BULLET, A3_ICE_SHARD, A3_LAETITIA,
                    A3_BLOOD, A3_SOLEMN_LAMENT, A3_GRINDER_MK4, A2_FOURTH_MATCH_FIRE, A2_RED_EYE,
                    A2_REGLET, A2_SOMEWHERE -> true;
            default -> false;
        };
    }

    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        return Curios.CORE_PAGE_SLOT.equals(slotContext.identifier());
    }

    @Override
    public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
        return canEquip(slotContext, stack);
    }

    @Override
    public @NotNull ICurio.SoundInfo getEquipSound(SlotContext slotContext, ItemStack stack) {
        return new ICurio.SoundInfo(material.getEquipSound(), 1.0f, 1.0f);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> attributes = ImmutableMultimap.builder();
        int protection = material.getDefenseForType(ArmorItem.Type.CHESTPLATE)
                + material.getDefenseForType(ArmorItem.Type.LEGGINGS);
        attributes.put(Attributes.ARMOR, new AttributeModifier(uuid, "Core page armor", protection, AttributeModifier.Operation.ADDITION));
        attributes.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(uuid, "Core page armor toughness", material.getToughness(), AttributeModifier.Operation.ADDITION));
        attributes.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(uuid, "Core page knockback resistance", material.getKnockbackResistance(), AttributeModifier.Operation.ADDITION));
        return attributes.build();
    }

    private PlayState predicate(AnimationState<CorePageItem> animationState) {
        animationState.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
