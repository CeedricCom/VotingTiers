package com.ceedric.votingtiers.rewards;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

public class BroadcastReward implements Reward {

    protected final List<String> messages;

    public BroadcastReward(List<String> messages) {
        this.messages = messages;
    }

    @Override
    public void executeReward(Player player) {
        for (String message : messages) {
            String send = ChatColor.translateAlternateColorCodes('&', message);
            Bukkit.broadcastMessage(PlaceholderAPI.setPlaceholders(player, send));
        }
    }
}
