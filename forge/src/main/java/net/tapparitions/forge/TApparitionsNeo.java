package net.tapparitions.forge;

import net.tapparitions.TApparitionsMod;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(TApparitionsMod.MOD_ID)
public class TApparitionsNeo {
    public TApparitionsNeo() {
        // Submit our event bus to let architectury register our content on the right time
        TApparitionsMod.init();
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
    }
}
