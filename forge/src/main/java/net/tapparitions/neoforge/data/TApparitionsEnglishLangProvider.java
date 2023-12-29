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
        addUpgrade(TAUpgrades.HEALING_II.get(), "Recovery I", "Allows the TARDIS to remove any negative effect");
        addUpgrade(TAUpgrades.PROTECTION_I.get(), "Protection I", "Makes sure players wont get lethal damage");
        addUpgrade(TAUpgrades.ER_I.get(), "Emergency Response", "If player was to reach a near-death situation, the TARDIS will grant it strong Regeneration");

        addUpgrade(TAUpgrades.PROTECTION_II.get(), "Protection II", "Protects anyone inside the TARDIS, doesn't let their health go below 25%");
        addUpgrade(TAUpgrades.PROTECTION_III.get(), "Protection III", "Protects anyone inside the TARDIS, doesn't let their health go below 50%");
        addUpgrade(TAUpgrades.PROTECTION_IV.get(), "Protection IV", "Protects anyone inside the TARDIS, doesn't let their health drop.");
        addUpgrade(TAUpgrades.PROTECTION_V.get(), "Protection V", "Protects anyone in 16 range from the exterior from all incoming damage");

        addUpgrade(TAUpgrades.HEALING_III.get(), "Recovery II", "Greatly enhances the basic healing capabilities of a TARDIS");
        addUpgrade(TAUpgrades.HEALING_IV.get(), "Recovery III", "Gives everyone in the TARDIS some beneficial effects");
    }

    private void addUpgrade(Upgrade upgrade, String title, String description) {
        add(Util.makeDescriptionId("upgrade", Upgrades.UPGRADE_REGISTRY.getKey(upgrade)), title);
        add(Util.makeDescriptionId("upgrade", Upgrades.UPGRADE_REGISTRY.getKey(upgrade)) + ".description", description);
    }

}
