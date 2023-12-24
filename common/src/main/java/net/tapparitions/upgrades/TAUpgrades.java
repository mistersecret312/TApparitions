package net.tapparitions.upgrades;

import net.minecraft.world.item.Items;
import net.tapparitions.TApparitionsMod;
import whocraft.tardis_refined.common.capability.upgrades.IncrementUpgrade;
import whocraft.tardis_refined.common.capability.upgrades.Upgrade;
import whocraft.tardis_refined.common.capability.upgrades.Upgrades;
import whocraft.tardis_refined.registry.DeferredRegistry;
import whocraft.tardis_refined.registry.RegistrySupplier;

import static net.tapparitions.TApparitionsMod.makeKey;

public class TAUpgrades {

    public static final DeferredRegistry<Upgrade> UPGRADE_DEFERRED_REGISTRY = DeferredRegistry.create(TApparitionsMod.MOD_ID, Upgrades.UPGRADE_REGISTRY_KEY);
    // Base Upgrades
    public static final RegistrySupplier<Upgrade> EXPLORER_IV = UPGRADE_DEFERRED_REGISTRY.register("explorer_iv", () ->
            new IncrementUpgrade(Items.COMPASS::getDefaultInstance, Upgrades.EXPLORER_III, makeKey("explorer_iv"), Upgrade.UpgradeType.SUB_UPGRADE)
                    .setIncrementAmount(10000).setSkillPointsRequired(75).setPosition(4, 2));

}
