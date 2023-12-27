package net.tapparitions.fabric;

import net.tapparitions.upgrades.TAUpgrades;
import whocraft.tardis_refined.common.capability.TardisLevelOperator;
import whocraft.tardis_refined.registry.DimensionTypes;

import static net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents.START_WORLD_TICK;

public class ModEvents {

    public static void addCommonEvents() {

        START_WORLD_TICK.register(world -> {
            if (world.dimensionTypeId().location() == DimensionTypes.TARDIS.location()) {
                TardisLevelOperator operator = TardisLevelOperator.get(world).get();
                if(operator.getUpgradeHandler().isUpgradeUnlocked(TAUpgrades.HEALING_I.get())){
                    operator.getLevel().getServer().getLevel(operator.getLevel().dimension()).players().forEach(player -> {
                        player.heal(1f);
                    });
                };
            }
        });
    }

}
