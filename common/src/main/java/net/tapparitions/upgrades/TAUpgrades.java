package net.tapparitions.upgrades;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.tapparitions.TApparitionsMod;
import whocraft.tardis_refined.common.capability.upgrades.IncrementUpgrade;
import whocraft.tardis_refined.common.capability.upgrades.Upgrade;
import whocraft.tardis_refined.common.capability.upgrades.Upgrades;
import whocraft.tardis_refined.registry.DeferredRegistry;
import whocraft.tardis_refined.registry.RegistrySupplier;

import static net.tapparitions.TApparitionsMod.makeKey;

public class TAUpgrades {

    public static final DeferredRegistry<Upgrade> UPGRADE_DEFERRED_REGISTRY = DeferredRegistry.create(TApparitionsMod.MOD_ID, Upgrades.UPGRADE_REGISTRY_KEY);

    public static final RegistrySupplier<Upgrade> EXPLORER_IV = UPGRADE_DEFERRED_REGISTRY.register("explorer_iv", () ->
            new IncrementUpgrade(Items.RECOVERY_COMPASS::getDefaultInstance, Upgrades.EXPLORER_III, makeKey("explorer_iv"), Upgrade.UpgradeType.SUB_UPGRADE)
                    .setIncrementAmount(10000).setSkillPointsRequired(75).setPosition(4, 2));

    public static final RegistrySupplier<Upgrade> HEALING_I = UPGRADE_DEFERRED_REGISTRY.register("healing_i", () ->
            new PositionUpgrade(() -> createPotionStack(Potions.HEALING), Upgrades.DEFENSE_SYSTEM, makeKey("healing_i"), Upgrade.UpgradeType.MAIN_UPGRADE)
                    .setPositionFloat(-5f, 1.8f).setSkillPointsRequired(75));

    public static final RegistrySupplier<Upgrade> HEALING_II = UPGRADE_DEFERRED_REGISTRY.register("recovery_ii", () ->
            new PositionUpgrade(() -> createPotionStack(Potions.STRONG_HEALING), TAUpgrades.HEALING_I, makeKey("recovery_ii"), Upgrade.UpgradeType.SUB_UPGRADE)
                    .setPositionFloat(-5.8f, 2.6f).setSkillPointsRequired(75));

    public static final RegistrySupplier<Upgrade> HEALING_III = UPGRADE_DEFERRED_REGISTRY.register("healing_iii", () ->
            new PositionUpgrade(() -> createPotionStack(Potions.STRONG_HEALING), TAUpgrades.HEALING_II, makeKey("healing_iii"), Upgrade.UpgradeType.SUB_UPGRADE)
                    .setPositionFloat(-5.8f, 3.4f).setSkillPointsRequired(75));

    public static final RegistrySupplier<Upgrade> HEALING_IV = UPGRADE_DEFERRED_REGISTRY.register("healing_iv", () ->
            new PositionUpgrade(() -> createPotionStack(Potions.STRONG_HEALING), TAUpgrades.HEALING_III, makeKey("healing_iv"), Upgrade.UpgradeType.SUB_UPGRADE)
                    .setPositionFloat(-5.8f, 4.2f).setSkillPointsRequired(75));

    public static final RegistrySupplier<Upgrade> PROTECTION_I = UPGRADE_DEFERRED_REGISTRY.register("protection_i", () ->
            new PositionUpgrade(Items.SHIELD::getDefaultInstance, TAUpgrades.HEALING_I, makeKey("protection_i"), Upgrade.UpgradeType.SUB_UPGRADE)
                    .setPositionFloat(-5f, 2.6f).setSkillPointsRequired(75));

    public static final RegistrySupplier<Upgrade> PROTECTION_II = UPGRADE_DEFERRED_REGISTRY.register("protection_ii", () ->
            new PositionUpgrade(Items.SHIELD::getDefaultInstance, TAUpgrades.PROTECTION_I, makeKey("protection_ii"), Upgrade.UpgradeType.SUB_UPGRADE)
                    .setPositionFloat(-5f, 3.4f).setSkillPointsRequired(75));

    public static final RegistrySupplier<Upgrade> PROTECTION_III = UPGRADE_DEFERRED_REGISTRY.register("protection_iii", () ->
            new PositionUpgrade(Items.SHIELD::getDefaultInstance, TAUpgrades.PROTECTION_II, makeKey("protection_iii"), Upgrade.UpgradeType.SUB_UPGRADE)
                    .setPositionFloat(-5f, 4.2f).setSkillPointsRequired(75));

    public static final RegistrySupplier<Upgrade> PROTECTION_IV = UPGRADE_DEFERRED_REGISTRY.register("protection_iv", () ->
            new PositionUpgrade(Items.SHIELD::getDefaultInstance, TAUpgrades.PROTECTION_III, makeKey("protection_iv"), Upgrade.UpgradeType.SUB_UPGRADE)
                    .setPositionFloat(-5f, 5f).setSkillPointsRequired(75));

    public static final RegistrySupplier<Upgrade> PROTECTION_V = UPGRADE_DEFERRED_REGISTRY.register("protection_v", () ->
            new PositionUpgrade(Items.SHIELD::getDefaultInstance, TAUpgrades.PROTECTION_IV, makeKey("protection_v"), Upgrade.UpgradeType.SUB_UPGRADE)
                    .setPositionFloat(-5f, 5.8f).setSkillPointsRequired(75));

    public static final RegistrySupplier<Upgrade> ER_I = UPGRADE_DEFERRED_REGISTRY.register("er_i", () ->
            new PositionUpgrade(Items.APPLE::getDefaultInstance, TAUpgrades.HEALING_I, makeKey("er_i"), Upgrade.UpgradeType.SUB_UPGRADE)
                    .setPositionFloat(-4.2f, 2.6f).setSkillPointsRequired(75));

    public static final RegistrySupplier<Upgrade> ENVIR_PROT = UPGRADE_DEFERRED_REGISTRY.register("ev_o", () ->
            new PositionUpgrade(Items.AZALEA_LEAVES::getDefaultInstance, TAUpgrades.HEALING_I, makeKey("ev_o"), Upgrade.UpgradeType.SUB_UPGRADE)
                    .setPositionFloat(-4.2f, 2.6f).setSkillPointsRequired(75));



    public static ItemStack createPotionStack(Potion potion){
        ItemStack itemStack = new ItemStack(Items.POTION);
        PotionUtils.setPotion(itemStack, potion);
        return itemStack;
    }
}
