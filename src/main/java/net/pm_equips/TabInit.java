package net.pm_equips;

import net.pm_equips.tabs.PMBaseTabs;
import net.pm_equips.tabs.PMFixerWeaponTabs;
import net.pm_equips.tabs.PMLobotomyEGOTabs;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.pm_equips.tabs.PMWCorpTabs;

public class TabInit {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PMEquipsMain.MOD_ID);

    public static final RegistryObject<CreativeModeTab> PM_LOBOTOMY_EGO = CREATIVE_TABS.register("pm_lobotomy_ego",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.pm_lobotomy_ego"))
                    .icon(() -> ItemInit.W1_PENITENCE.get().getDefaultInstance())
                    .displayItems((param, output) -> {
                        for (Item item : PMLobotomyEGOTabs.items) {
                            output.accept(item);
                        }
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> PM_BASE = CREATIVE_TABS.register("pm_base",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.pm_base"))
                    .icon(() -> BlockInit.BlockItems.ZAYIN_PE_BOX.get().getDefaultInstance())
                    .displayItems((param, output) -> {
                        for (Item item : PMBaseTabs.items) {
                            output.accept(item);
                        }
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> PM_FIXERS = CREATIVE_TABS.register("pm_fixers",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.pm_fixers"))
                    .icon(() -> ItemInit.EX_DURANDAL.get().getDefaultInstance())
                    .displayItems((param, output) -> {
                        for (Item item : PMFixerWeaponTabs.items) {
                            output.accept(item);
                        }
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> PM_WCORP = CREATIVE_TABS.register("pm_wcorp",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.pm_wcorp"))
                    .icon(() -> ItemInit.WCORP_ARMOR_1.get().getDefaultInstance())
                    .displayItems((param, output) -> {
                        for (Item item : PMWCorpTabs.items) {
                            output.accept(item);
                        }
                    })
                    .build());
}
