package net.lobotomy_corporation_mod;

import net.lobotomy_corporation_mod.blocks.EBoxGen;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockInit {
    public static class Blocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Lobotomy_corporation_mod.MOD_ID);

    //PE-Box
    public static final RegistryObject<Block> ZAYIN_PE_BOX = BLOCKS.register("e_box_1",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1.0f, 50f)
                    .sound(SoundType.METAL)
                    .instabreak()));

        public static final RegistryObject<Block> TETH_PE_BOX = BLOCKS.register("e_box_2",
                () -> new Block(BlockBehaviour.Properties.of()
                        .strength(1.0f, 50f)
                        .sound(SoundType.METAL)
                        .instabreak()));

        public static final RegistryObject<Block> HE_PE_BOX = BLOCKS.register("e_box_3",
                () -> new Block(BlockBehaviour.Properties.of()
                        .strength(1.0f, 50f)
                        .sound(SoundType.METAL)
                        .instabreak()));

        public static final RegistryObject<Block> WAW_PE_BOX = BLOCKS.register("e_box_4",
                () -> new Block(BlockBehaviour.Properties.of()
                        .strength(1.0f, 50f)
                        .sound(SoundType.METAL)
                        .instabreak()));

        public static final RegistryObject<Block> ALEPH_PE_BOX = BLOCKS.register("e_box_5",
                () -> new Block(BlockBehaviour.Properties.of()
                        .strength(1.0f, 50f)
                        .sound(SoundType.METAL)
                        .instabreak()));

        public static final RegistryObject<Block> E_GEN = BLOCKS.register("e_gen",
                () -> new EBoxGen(BlockBehaviour.Properties.of()
                        .mapColor(MapColor.STONE)
                        .strength(3.0F)
                        .sound(SoundType.METAL)
                        .instabreak()));
    }

    public static class BlockItems {
        public static final DeferredRegister<Item> BLOCK_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Lobotomy_corporation_mod.MOD_ID);


        public static final RegistryObject<Item> ZAYIN_PE_BOX = BLOCK_ITEMS.register("e_box_1",
                () -> new BlockItem(Blocks.ZAYIN_PE_BOX.get(), new Item.Properties().stacksTo(64)));

        public static final RegistryObject<Item> TETH_PE_BOX = BLOCK_ITEMS.register("e_box_2",
                () -> new BlockItem(Blocks.TETH_PE_BOX.get(), new Item.Properties().stacksTo(64)));

        public static final RegistryObject<Item> HE_PE_BOX = BLOCK_ITEMS.register("e_box_3",
                () -> new BlockItem(Blocks.HE_PE_BOX.get(), new Item.Properties().stacksTo(64)));

        public static final RegistryObject<Item> WAW_PE_BOX = BLOCK_ITEMS.register("e_box_4",
                () -> new BlockItem(Blocks.WAW_PE_BOX.get(), new Item.Properties().stacksTo(64)));

        public static final RegistryObject<Item> ALEPH_PE_BOX = BLOCK_ITEMS.register("e_box_5",
                () -> new BlockItem(Blocks.ALEPH_PE_BOX.get(), new Item.Properties().stacksTo(64)));

        public static final RegistryObject<Item> E_GEN = BLOCK_ITEMS.register("e_gen",
                () -> new BlockItem(Blocks.E_GEN.get(), new Item.Properties()));
    }
}
