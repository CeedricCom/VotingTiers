package com.ceedric.votingtiers.command;

import com.ceedric.votingtiers.VotingTiers;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class EvaluateRewardCommand implements CommandExecutor {

    private final VotingTiers plugin;

    public EvaluateRewardCommand(VotingTiers plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.hasPermission("votingtiers.evaluate"))
            return true;

        String name = args[0];

        Player player = Bukkit.getPlayer(name);

        if (player == null || !player.isOnline())
            return true;


        plugin.getTierManager().handleVote(player.getUniqueId());

        return true;
    }
}
