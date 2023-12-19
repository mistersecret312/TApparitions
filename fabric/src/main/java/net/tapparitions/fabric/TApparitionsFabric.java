package net.tapparitions.fabric;

import net.fabricmc.api.ModInitializer;
import net.tapparitions.TApparitionsMod;

public class TApparitionsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        TApparitionsMod.init();
    }
}
