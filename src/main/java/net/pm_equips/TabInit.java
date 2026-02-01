package net.pm_equips;

import net.pm_equips.tabs.PMBlockTabs;
import net.pm_equips.tabs.PMItemTabs;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class TabInit {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PMEquipsMain.MOD_ID);

    public static final RegistryObject<CreativeModeTab> PM_LOBOTOMY_EGO = CREATIVE_TABS.register("pm_lobotomy_ego",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.pm_lobotomy_ego"))
                    .icon(() -> ItemInit.W1_PENITENCE.get().getDefaultInstance())
                    .displayItems((param, output) -> {
                        for (Item item : PMItemTabs.items) {
                            output.accept(item);
                        }
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> PM_BASE = CREATIVE_TABS.register("pm_base",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.pm_base"))
                    .icon(() -> BlockInit.BlockItems.ZAYIN_PE_BOX.get().getDefaultInstance())
                    .displayItems((param, output) -> {
                        for (Item item : PMBlockTabs.items) {
                            output.accept(item);
                        }
                    })
                    .build());
}
