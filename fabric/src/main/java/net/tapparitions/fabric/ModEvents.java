package net.tapparitions.fabric;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
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
                        if(operator.getLevel().getGameTime() % 200 == 0) {
                            player.heal(2f);
                        }
                    });
                }
                if(operator.getUpgradeHandler().isUpgradeUnlocked(TAUpgrades.HEALING_II.get())){
                    operator.getLevel().getServer().getLevel(operator.getLevel().dimension()).players().forEach(player -> {
                        if(operator.getLevel().getGameTime() % 20 == 0) {
                            if(player.getActiveEffectsMap().containsKey(MobEffects.DIG_SLOWDOWN)) {
                                player.removeEffect(MobEffects.DIG_SLOWDOWN);
                            }
                            if(player.getActiveEffectsMap().containsKey(MobEffects.MOVEMENT_SLOWDOWN)) {
                                player.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
                            }
                            if(player.getActiveEffectsMap().containsKey(MobEffects.WITHER)) {
                                player.removeEffect(MobEffects.WITHER);
                            }
                            if(player.getActiveEffectsMap().containsKey(MobEffects.WEAKNESS)) {
                                player.removeEffect(MobEffects.WEAKNESS);
                            }
                            if(player.getActiveEffectsMap().containsKey(MobEffects.POISON)) {
                                player.removeEffect(MobEffects.POISON);
                            }
                        }
                    });
                }
            }
        });
        ServerLivingEntityEvents.ALLOW_DEATH.register((entity, source, amount) -> {
            Level world = entity.level();
            if (world instanceof ServerLevel serverLevel) {
                if (world.dimensionTypeId().location() == DimensionTypes.TARDIS.location()) {
                    TardisLevelOperator operator = TardisLevelOperator.get(serverLevel).get();
                    if (operator.getUpgradeHandler().isUpgradeUnlocked(TAUpgrades.PROTECTION_I.get()) && entity instanceof Player) {
                        return entity.getHealth()<amount;
                    }
                }
            }
            return true;
        });

        ServerLivingEntityEvents.ALLOW_DAMAGE.register((entity, source, amount) -> {
            Level world = entity.level();
            if (world instanceof ServerLevel serverLevel) {
                if (world.dimensionTypeId().location() == DimensionTypes.TARDIS.location()) {
                    TardisLevelOperator operator = TardisLevelOperator.get(serverLevel).get();
                    if (operator.getUpgradeHandler().isUpgradeUnlocked(TAUpgrades.ER_I.get())) {
                        if ((entity.getHealth()-amount) / entity.getMaxHealth() < 0.3 && entity instanceof Player) {
                            if((entity.getHealth()-amount)/entity.getMaxHealth() < 0 && !operator.getUpgradeHandler().isUpgradeUnlocked(TAUpgrades.PROTECTION_I.get())){
                                amount /= 2;
                            }
                            entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 80, 5, false, false, false));
                        }
                    }
                }
            }

            return true;
        });
    }

}
