package net.tapparitions.neoforge.events;

import net.minecraft.world.item.Item;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.tapparitions.TApparitionsMod;
import net.tapparitions.items.TAItems;
import whocraft.tardis_refined.registry.RegistrySupplier;

@Mod.EventBusSubscriber(modid = TApparitionsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModBus {

    @SubscribeEvent
    public static void onBuildTabsContent(BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() == TAItems.MAIN_TAB.get()) {
            for (RegistrySupplier<Item> item : TAItems.TAB_ITEMS.stream().toList()) {
                event.accept(item.get());
            }
        }
    }
}