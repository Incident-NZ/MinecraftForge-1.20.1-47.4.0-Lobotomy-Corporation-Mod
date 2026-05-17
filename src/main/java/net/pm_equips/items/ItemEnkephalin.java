package net.pm_equips.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.pm_equips.Interface.IFEFuelItem;

public class ItemEnkephalin extends Item implements IFEFuelItem {
    public ItemEnkephalin(Properties properties) {
        super(properties);
    }

    @Override
    public int getFEValue(ItemStack stack) {
        return 100000;
    }
}
