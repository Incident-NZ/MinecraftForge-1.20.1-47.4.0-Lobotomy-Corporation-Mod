package net.lobotomy_corporation_mod.Events;

import net.lobotomy_corporation_mod.Lobotomy_corporation_mod;
import net.lobotomy_corporation_mod.items.W5Justitia;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Lobotomy_corporation_mod.MOD_ID, value = Dist.CLIENT)
public class CommonEvents {

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        W5Justitia.onLivingDeath(event);
    }
}
