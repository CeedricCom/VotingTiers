package com.ceedric.votingtiers.rewards;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class ItemReward implements Reward {

    private final ItemStack itemStack;

    public ItemReward(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    @Override
    public void executeReward(Player player) {
        HashMap<Integer, ItemStack> toDrop = player.getInventory().addItem(itemStack);

        for (int i : toDrop.keySet())
            player.getWorld().dropItem(player.getLocation(), toDrop.get(i));

    }
}
