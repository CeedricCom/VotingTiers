package com.ceedric.votingtiers.rewards;

import com.ceedric.votingtiers.VotingTiers;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandReward implements Reward {

    private final List<String> commands;
    private final VotingTiers plugin;

    public CommandReward(VotingTiers plugin, List<String> commands) {
        this.commands = commands;
        this.plugin = plugin;
    }

    @Override
    public void executeReward(Player player) {
        Bukkit.getScheduler().runTask(plugin, () -> {
            for (String cmd : new ArrayList<>(commands)) {
                String execute = cmd.replace("%player%", player.getName());
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), execute);
            }
        });
    }
}
