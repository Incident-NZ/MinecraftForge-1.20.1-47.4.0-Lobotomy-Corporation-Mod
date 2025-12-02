package net.lobotomy_corporation_mod;

import net.lobotomy_corporation_mod.tabs.PMBlockTabs;
import net.lobotomy_corporation_mod.tabs.PMItemTabs;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class TabInit {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Lobotomy_corporation_mod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> PM_ITEMS = CREATIVE_TABS.register("pm_items",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.pm_items"))
                    .icon(() -> ItemInit.TASK_TABLET.get().getDefaultInstance())
                    .displayItems((param, output) -> {
                        for (Item item : PMItemTabs.items) {
                            output.accept(item);
                        }
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> PM_BLOCKS = CREATIVE_TABS.register("pm_blocks",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.pm_blocks"))
                    .icon(() -> BlockInit.BlockItems.ZAYIN_PE_BOX.get().getDefaultInstance())
                    .displayItems((param, output) -> {
                        for (Item item : PMBlockTabs.items) {
                            output.accept(item);
                        }
                    })
                    .build());
}
