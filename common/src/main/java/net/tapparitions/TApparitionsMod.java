package net.tapparitions;

import net.tapparitions.items.TAItems;

public class TApparitionsMod {
    public static final String MOD_ID = "tapparitions";
    public static final String PLATFORM_ERROR = "Uh oh, Platform definitions have fallen out of the bed";

    public static void init() {
        TAItems.TABS.register();
        TAItems.ITEMS.register();
    }
}
