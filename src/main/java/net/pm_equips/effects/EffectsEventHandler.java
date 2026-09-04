package net.pm_equips.effects;

import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.pm_equips.MobEffectInit;

@Mod.EventBusSubscriber(modid = "pm_equips", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EffectsEventHandler {

    @SubscribeEvent
    public static void onLivingAttack(LivingAttackEvent event) {
        if (event.getEntity().level().isClientSide) return;
        LivingEntity target = event.getEntity();

        // W.Corp singularity evade handling
        if (target.hasEffect(MobEffectInit.WCORP_SIN.get())) {
            var tag = target.getPersistentData();
            int charges = tag.getInt("wcorp_evade_charges");
            if (charges > 0) {
                tag.putInt("wcorp_evade_charges", charges - 1);
                event.setCanceled(true);
                return;
            }
        }
    }
}
