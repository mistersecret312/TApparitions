package net.tapparitions.forge;

import net.minecraft.data.DataGenerator;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.tapparitions.TApparitionsMod;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;
import net.tapparitions.forge.data.TApparitionsEnglishLangProvider;

@Mod(TApparitionsMod.MOD_ID)
public class TApparitionsNeo {
    public TApparitionsNeo() {
        // Submit our event bus to let architectury register our content on the right time
        TApparitionsMod.init();
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::onGatherData);

    }


    public void onGatherData(GatherDataEvent e) {
        DataGenerator generator = e.getGenerator();
        ExistingFileHelper existingFileHelper = e.getExistingFileHelper();

        /*Resource Pack*/
        generator.addProvider(e.includeClient(), new TApparitionsEnglishLangProvider(generator.getPackOutput()));
    }
}
