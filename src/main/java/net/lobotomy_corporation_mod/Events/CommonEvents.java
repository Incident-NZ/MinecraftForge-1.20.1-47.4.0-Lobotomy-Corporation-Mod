package net.lobotomy_corporation_mod.Events;

import net.lobotomy_corporation_mod.items.W5Justitia;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "lobotomy_corporation_mod", value = Dist.CLIENT)
public class CommonEvents {

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        W5Justitia.onLivingDeath(event);
    }
}
