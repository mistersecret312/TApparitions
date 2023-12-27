package net.tapparitions.forge.events;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.TickEvent;
import net.tapparitions.TApparitionsMod;
import net.tapparitions.upgrades.TAUpgrades;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.capability.TardisLevelOperator;
import whocraft.tardis_refined.common.dimension.TardisTeleportData;
import whocraft.tardis_refined.registry.DimensionTypes;

@Mod.EventBusSubscriber(modid = TApparitionsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommonBus {

    @SubscribeEvent
    public static void onLevelTick(TickEvent.LevelTickEvent event){
        Level level = event.level;
        if (level instanceof ServerLevel serverLevel) {
            if (event.phase == TickEvent.Phase.START) {
                if (level.dimensionTypeId().location() == DimensionTypes.TARDIS.location()) {
                    TardisLevelOperator operator = TardisLevelOperator.get(serverLevel).get();
                    if(operator.getUpgradeHandler().isUpgradeUnlocked(TAUpgrades.HEALING_I.get())){
                        operator.getLevel().getServer().getLevel(operator.getLevel().dimension()).players().forEach(player -> {
                            player.heal(1f);
                        });
                    }
                }
            }
        }
    }

}
