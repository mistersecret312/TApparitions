package net.tapparitions.fabric;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.tapparitions.upgrades.TAUpgrades;
import whocraft.tardis_refined.api.event.TardisEvents;
import whocraft.tardis_refined.common.capability.TardisLevelOperator;
import whocraft.tardis_refined.common.tardis.ExteriorShell;
import whocraft.tardis_refined.common.tardis.TardisNavLocation;
import whocraft.tardis_refined.common.tardis.manager.TardisExteriorManager;
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
                if(operator.getUpgradeHandler().isUpgradeUnlocked(TAUpgrades.HEALING_IV.get())){
                    operator.getLevel().getServer().getLevel(operator.getLevel().dimension()).players().forEach(player -> player.addEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 8, 5, false, false, true)));
                }
            }
        });

        ServerLivingEntityEvents.ALLOW_DAMAGE.register((entity, source, amount) -> {
            Level world = entity.level();
            if (world instanceof ServerLevel serverLevel) {
                if (world.dimensionTypeId().location() == DimensionTypes.TARDIS.location()) {
                    TardisLevelOperator operator = TardisLevelOperator.get(serverLevel).get();
                    if (operator.getUpgradeHandler().isUpgradeUnlocked(TAUpgrades.ER_I.get())) {
                        if ((entity.getHealth() - amount) / Math.max(1,entity.getMaxHealth()) < 0.3 && entity instanceof Player) {
                            if ((entity.getHealth() - amount) / Math.max(1,entity.getMaxHealth()) < 0 && !operator.getUpgradeHandler().isUpgradeUnlocked(TAUpgrades.PROTECTION_I.get())) {
                                amount /= 2;
                            }
                            entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 80, 5, false, false, false));
                        }
                    }
                    if (operator.getUpgradeHandler().isUpgradeUnlocked(TAUpgrades.PROTECTION_I.get())) {
                        return entity.getHealth() > amount;
                    }
                    if (operator.getUpgradeHandler().isUpgradeUnlocked(TAUpgrades.PROTECTION_II.get())) {
                        return entity.getHealth() / Math.max(1,entity.getMaxHealth()) > 0.25;
                    }
                    if (operator.getUpgradeHandler().isUpgradeUnlocked(TAUpgrades.PROTECTION_III.get())) {
                        return entity.getHealth() / Math.max(1,entity.getMaxHealth()) > 0.5;
                    }
                    if (operator.getUpgradeHandler().isUpgradeUnlocked(TAUpgrades.PROTECTION_V.get())) {
                        BlockPos pos = operator.getExteriorManager().getLastKnownLocation().getPosition();
                        AABB ab = new AABB(pos).inflate(16);
                        for (Entity entity1 : operator.getExteriorManager().getLevel().getEntities(null, ab)) {
                            if (entity == entity1 && entity1 instanceof Player) {
                                return false;
                            }
                        }
                    }
                }
            }

            return true;
        });
    }

}
