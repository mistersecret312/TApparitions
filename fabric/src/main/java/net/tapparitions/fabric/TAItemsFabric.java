package net.tapparitions.fabric;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.tapparitions.items.TAItems;

public class TAItemsFabric {

    public static final CreativeModeTab ITEM_GROUP = FabricItemGroup.builder().icon(() -> new ItemStack(BlockRegistry.GLOBAL_CONSOLE_BLOCK.get())).displayItems((enabledFeatures, entries) -> {
        for (RegistrySupplier<Item> item : TAItems.TAB_ITEMS) {
            entries.accept(item.get());
        }
    }).title(Component.literal("Transcendental Apparitions")).build();

    public static CreativeModeTab getCreativeTab() {
        return ITEM_GROUP;
    }

}
