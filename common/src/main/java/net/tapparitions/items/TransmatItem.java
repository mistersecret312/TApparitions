package net.tapparitions.items;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.RelativeMovement;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import whocraft.tardis_refined.common.tardis.TardisNavLocation;

public class TransmatItem extends Item {

    public TransmatItem(Properties properties) {
        super(properties);
    }

    public void setItemData(ItemStack itemStack, TardisNavLocation nav){
        CompoundTag tag = itemStack.getOrCreateTag();
        tag.put("nav", nav.serialise());
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        TardisNavLocation lastLoc = TardisNavLocation.deserialise(player.getItemInHand(interactionHand).getTag());
        if(lastLoc != null) {
            player.teleportTo(lastLoc.getLevel(), lastLoc.getPosition().getX(), lastLoc.getPosition().getY(), lastLoc.getPosition().getZ(), RelativeMovement.ROTATION, lastLoc.getDirection().toYRot(), 180);
        }
        return super.use(level, player, interactionHand);
    }
}
