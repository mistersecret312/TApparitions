package net.tapparitions.upgrades;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec2;
import org.jetbrains.annotations.Nullable;
import whocraft.tardis_refined.common.capability.upgrades.Upgrade;

import java.util.function.Supplier;

public class PositionUpgrade extends Upgrade {
    public float posXi = 0F;
    public float posYi = 0F;

    public PositionUpgrade(Supplier<ItemStack> icon, Supplier<Upgrade> parent, ResourceLocation translationKey, UpgradeType upgradeType) {
        super(icon, parent, translationKey, upgradeType);
    }

    public Upgrade setPositionFloat(float x, float y) {
        this.posXi = x;
        this.posYi = y;
        this.setPosition((int) x, (int) y);
        return this;
    }

    @Override
    public @Nullable Vec2 getScreenPosition() {
        return new Vec2(this.posXi, this.posYi);
    }
}
