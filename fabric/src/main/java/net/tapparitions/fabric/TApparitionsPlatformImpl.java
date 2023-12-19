package net.tapparitions.fabric;

import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

public class TApparitionsPlatformImpl {
    /**
     * This is our actual method to {@link net.examplemod.TApparitionsExpectPlatform#getConfigDirectory()}.
     */
    public static Path getConfigDirectory() {
        return FabricLoader.getInstance().getConfigDir();
    }
}
