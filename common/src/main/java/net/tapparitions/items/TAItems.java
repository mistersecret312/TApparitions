package net.tapparitions.items;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.tapparitions.TApparitionsMod;
import whocraft.tardis_refined.registry.DeferredRegistry;
import whocraft.tardis_refined.registry.ItemRegistry;
import whocraft.tardis_refined.registry.RegistrySupplier;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class TAItems {

    public static List<RegistrySupplier<Item>> TAB_ITEMS = new ArrayList<>();
    public static final DeferredRegistry<CreativeModeTab> TABS = DeferredRegistry.create(TApparitionsMod.MOD_ID, Registries.CREATIVE_MODE_TAB);

    public static final RegistrySupplier<CreativeModeTab> MAIN_TAB = TABS.register("tapparitions", TAItems::getCreativeTab);



    public static final DeferredRegistry<Item> ITEMS = DeferredRegistry.create(TApparitionsMod.MOD_ID, Registries.ITEM);

    public static final RegistrySupplier<Item> KEY = register("test", () -> new Item(new Item.Properties().stacksTo(1)), true);



    private static <T extends Item> RegistrySupplier<T> register(String id, Supplier<T> itemSupplier, boolean addToTab) {
        RegistrySupplier<T> item = ITEMS.register(id, itemSupplier);
        if(addToTab) {
            TAB_ITEMS.add((RegistrySupplier<Item>) item);
        }
        return item;
    }

    @ExpectPlatform
    public static CreativeModeTab getCreativeTab() {
        throw new RuntimeException(TApparitionsMod.PLATFORM_ERROR);
    }

}
