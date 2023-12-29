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
            new Upgrade(() -> createPotionStack(Potions.HEALING), Upgrades.DEFENSE_SYSTEM, makeKey("healing_i"), Upgrade.UpgradeType.MAIN_UPGRADE)
                    .setSkillPointsRequired(75).setPosition(-1, 1));

    public static final RegistrySupplier<Upgrade> HEALING_II = UPGRADE_DEFERRED_REGISTRY.register("recovery_ii", () ->
            new Upgrade(() -> createPotionStack(Potions.STRONG_HEALING), TAUpgrades.HEALING_I, makeKey("recovery_ii"), Upgrade.UpgradeType.SUB_UPGRADE)
                    .setSkillPointsRequired(75).setPosition(-2, 1));

    public static final RegistrySupplier<Upgrade> HEALING_III = UPGRADE_DEFERRED_REGISTRY.register("healing_iii", () ->
            new Upgrade(() -> createPotionStack(Potions.STRONG_HEALING), TAUpgrades.HEALING_II, makeKey("healing_iii"), Upgrade.UpgradeType.SUB_UPGRADE)
                    .setSkillPointsRequired(75).setPosition(-3, 1));

    public static final RegistrySupplier<Upgrade> HEALING_IV = UPGRADE_DEFERRED_REGISTRY.register("healing_iv", () ->
            new Upgrade(() -> createPotionStack(Potions.STRONG_HEALING), TAUpgrades.HEALING_III, makeKey("healing_iv"), Upgrade.UpgradeType.SUB_UPGRADE)
                    .setSkillPointsRequired(75).setPosition(-4, 1));

    public static final RegistrySupplier<Upgrade> PROTECTION_I = UPGRADE_DEFERRED_REGISTRY.register("protection_i", () ->
            new Upgrade(Items.SHIELD::getDefaultInstance, TAUpgrades.HEALING_I, makeKey("protection_i"), Upgrade.UpgradeType.SUB_UPGRADE)
                    .setSkillPointsRequired(75).setPosition(-2, 2));

    public static final RegistrySupplier<Upgrade> PROTECTION_II = UPGRADE_DEFERRED_REGISTRY.register("protection_ii", () ->
            new Upgrade(Items.SHIELD::getDefaultInstance, TAUpgrades.PROTECTION_I, makeKey("protection_ii"), Upgrade.UpgradeType.SUB_UPGRADE)
                    .setSkillPointsRequired(75).setPosition(-3, 2));

    public static final RegistrySupplier<Upgrade> PROTECTION_III = UPGRADE_DEFERRED_REGISTRY.register("protection_iii", () ->
            new Upgrade(Items.SHIELD::getDefaultInstance, TAUpgrades.PROTECTION_II, makeKey("protection_iii"), Upgrade.UpgradeType.SUB_UPGRADE)
                    .setSkillPointsRequired(75).setPosition(-4, 2));

    public static final RegistrySupplier<Upgrade> PROTECTION_IV = UPGRADE_DEFERRED_REGISTRY.register("protection_iv", () ->
            new Upgrade(Items.SHIELD::getDefaultInstance, TAUpgrades.PROTECTION_III, makeKey("protection_iv"), Upgrade.UpgradeType.SUB_UPGRADE)
                    .setSkillPointsRequired(75).setPosition(-5, 2));

    public static final RegistrySupplier<Upgrade> PROTECTION_V = UPGRADE_DEFERRED_REGISTRY.register("protection_v", () ->
            new Upgrade(Items.SHIELD::getDefaultInstance, TAUpgrades.PROTECTION_IV, makeKey("protection_v"), Upgrade.UpgradeType.SUB_UPGRADE)
                    .setSkillPointsRequired(75).setPosition(-6, 2));

    public static final RegistrySupplier<Upgrade> ER_I = UPGRADE_DEFERRED_REGISTRY.register("er_i", () ->
            new Upgrade(Items.APPLE::getDefaultInstance, TAUpgrades.HEALING_I, makeKey("er_i"), Upgrade.UpgradeType.SUB_UPGRADE)
                    .setSkillPointsRequired(75).setPosition(-2, 0));



    public static ItemStack createPotionStack(Potion potion){
        ItemStack itemStack = new ItemStack(Items.POTION);
        PotionUtils.setPotion(itemStack, potion);
        return itemStack;
    }
}
