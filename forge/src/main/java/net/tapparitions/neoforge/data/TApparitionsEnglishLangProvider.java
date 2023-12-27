package net.tapparitions.neoforge.data;

import net.minecraft.Util;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.tapparitions.TApparitionsMod;
import net.tapparitions.upgrades.TAUpgrades;
import whocraft.tardis_refined.common.capability.upgrades.Upgrade;
import whocraft.tardis_refined.common.capability.upgrades.Upgrades;

public class TApparitionsEnglishLangProvider extends LanguageProvider {

    public TApparitionsEnglishLangProvider(PackOutput output) {
        super(output, TApparitionsMod.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {

        addUpgrade(TAUpgrades.EXPLORER_IV.get(), "Exploration IV", "x10000 Increment");
        addUpgrade(TAUpgrades.HEALING_I.get(), "Medical Systems", "Basic Healing capabilities for everyone in the TARDIS");
    }

    private void addUpgrade(Upgrade upgrade, String title, String description) {
        add(Util.makeDescriptionId("upgrade", Upgrades.UPGRADE_REGISTRY.getKey(upgrade)), title);
        add(Util.makeDescriptionId("upgrade", Upgrades.UPGRADE_REGISTRY.getKey(upgrade)) + ".description", description);
    }

}
