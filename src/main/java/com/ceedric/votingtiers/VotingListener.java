package com.ceedric.votingtiers;

import com.vexsoftware.votifier.model.VotifierEvent;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class VotingListener implements Listener {

    private final VotingTiers plugin;

    public VotingListener(VotingTiers plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onVote(VotifierEvent event) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(event.getVote().getUsername());
            plugin.getTierManager().handleVote(offlinePlayer.getUniqueId());
        });
    }

}









