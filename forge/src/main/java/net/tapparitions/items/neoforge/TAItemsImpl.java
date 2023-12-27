package net.tapparitions.items.neoforge;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import whocraft.tardis_refined.registry.BlockRegistry;

public class TAItemsImpl {

    public static CreativeModeTab getCreativeTab() {
        return CreativeModeTab.builder().title(Component.translatable("Tardis Apparitions")).icon(() -> new ItemStack(BlockRegistry.GLOBAL_CONSOLE_BLOCK.get())).build();
    }
}
