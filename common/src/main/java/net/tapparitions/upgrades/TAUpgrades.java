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

    public static final RegistrySupplier<Upgrade> HEALING_II = UPGRADE_DEFERRED_REGISTRY.register("healing_ii", () ->
            new Upgrade(() -> createPotionStack(Potions.STRONG_HEALING), TAUpgrades.HEALING_I, makeKey("healing_ii"), Upgrade.UpgradeType.SUB_UPGRADE)
                    .setSkillPointsRequired(75).setPosition(-2, 1));

    public static final RegistrySupplier<Upgrade> PROTECTION_I = UPGRADE_DEFERRED_REGISTRY.register("protection_i", () ->
            new Upgrade(Items.SHIELD::getDefaultInstance, TAUpgrades.HEALING_I, makeKey("protection_i"), Upgrade.UpgradeType.SUB_UPGRADE)
                    .setSkillPointsRequired(75).setPosition(-2, 2));

    public static final RegistrySupplier<Upgrade> ER_I = UPGRADE_DEFERRED_REGISTRY.register("er_i", () ->
            new Upgrade(Items.APPLE::getDefaultInstance, TAUpgrades.HEALING_I, makeKey("er_i"), Upgrade.UpgradeType.SUB_UPGRADE)
                    .setSkillPointsRequired(75).setPosition(-2, 0));



    public static ItemStack createPotionStack(Potion potion){
        ItemStack itemStack = new ItemStack(Items.POTION);
        PotionUtils.setPotion(itemStack, potion);
        return itemStack;
    }
}
