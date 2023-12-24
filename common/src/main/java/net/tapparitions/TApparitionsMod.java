package net.tapparitions;

import net.minecraft.resources.ResourceLocation;
import net.tapparitions.items.TAItems;
import net.tapparitions.upgrades.TAUpgrades;

public class TApparitionsMod {
    public static final String MOD_ID = "tapparitions";
    public static final String PLATFORM_ERROR = "Uh oh, Platform definitions have fallen out of the bed";

    public static void init() {
        TAItems.TABS.register();
        TAItems.ITEMS.register();
        TAUpgrades.UPGRADE_DEFERRED_REGISTRY.register();
    }

    public static ResourceLocation makeKey(String id){
        return new ResourceLocation(MOD_ID, id);
    }
}
