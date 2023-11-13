package com.ceedric.votingtiers.rewards;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

public class MessageReward implements Reward {

    protected final List<String> messages;

    public MessageReward(List<String> messages) {
        this.messages = messages;
    }

    @Override
    public void executeReward(Player player) {
        for (String message : messages) {
            String send = ChatColor.translateAlternateColorCodes('&', message);
            player.sendMessage(PlaceholderAPI.setPlaceholders(player, send));
        }
    }
}
