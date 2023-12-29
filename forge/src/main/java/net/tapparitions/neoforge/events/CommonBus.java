package net.tapparitions.neoforge.events;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.TickEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.tapparitions.TApparitionsMod;
import net.tapparitions.upgrades.TAUpgrades;
import whocraft.tardis_refined.common.capability.TardisLevelOperator;
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
                    float timemod = 0;
                    if(operator.getUpgradeHandler().isUpgradeUnlocked(TAUpgrades.HEALING_II.get())){
                        timemod = 0.8f;
                    }
                    if(operator.getUpgradeHandler().isUpgradeUnlocked(TAUpgrades.HEALING_III.get())){
                        timemod = 0.5f;
                    }
                    float finalTimemod = timemod;
                    if(operator.getUpgradeHandler().isUpgradeUnlocked(TAUpgrades.HEALING_I.get())){
                        operator.getLevel().getServer().getLevel(operator.getLevel().dimension()).players().forEach(player -> {
                            if(operator.getLevel().getGameTime() % 200*finalTimemod == 0) {
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
                        operator.getLevel().getServer().getLevel(operator.getLevel().dimension()).players().forEach(player -> player.addEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 2, 5, false, false, true)));
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onDamagePlayer(LivingDamageEvent event){
        Level level = event.getEntity().level();
        LivingEntity entity = event.getEntity();
        if (level instanceof ServerLevel serverLevel) {
            if (level.dimensionTypeId().location() == DimensionTypes.TARDIS.location()) {
                TardisLevelOperator operator = TardisLevelOperator.get(serverLevel).get();
                if(operator.getUpgradeHandler().isUpgradeUnlocked(TAUpgrades.PROTECTION_I.get())) {
                    if (event.getEntity() instanceof Player) {
                        if(event.getAmount()>entity.getHealth()){
                            event.setCanceled(true);
                        }
                    }
                }
                if(operator.getUpgradeHandler().isUpgradeUnlocked(TAUpgrades.ER_I.get())){
                    if ((entity.getHealth()-event.getAmount()) / entity.getMaxHealth() < 0.1 && entity instanceof Player) {
                        if((entity.getHealth()-event.getAmount())/entity.getMaxHealth() < 0 && !operator.getUpgradeHandler().isUpgradeUnlocked(TAUpgrades.PROTECTION_I.get())){
                            event.setAmount(event.getAmount()/2);
                        }
                        entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 80, 5, false, false, false));
                    }
                }
                if(operator.getUpgradeHandler().isUpgradeUnlocked(TAUpgrades.PROTECTION_II.get())){
                    event.setCanceled(entity.getHealth()/entity.getMaxHealth() < 0.25);
                }
                if(operator.getUpgradeHandler().isUpgradeUnlocked(TAUpgrades.PROTECTION_III.get())){
                    event.setCanceled(entity.getHealth()/entity.getMaxHealth() < 0.5);
                }
                if(operator.getUpgradeHandler().isUpgradeUnlocked(TAUpgrades.PROTECTION_V.get())){
                    BlockPos pos = operator.getExteriorManager().getLastKnownLocation().getPosition();
                    AABB ab = new AABB(pos).inflate(16);
                    for(Entity entity1 : operator.getExteriorManager().getLevel().getEntities(null, ab)){
                        event.setCanceled(entity == entity1 && entity1 instanceof Player);
                    }
                }
            }
        }
    }

}
