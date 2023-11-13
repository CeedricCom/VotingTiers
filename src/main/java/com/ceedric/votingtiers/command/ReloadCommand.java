package com.ceedric.votingtiers.command;

import com.ceedric.votingtiers.VotingTiers;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ReloadCommand implements CommandExecutor {

    private final VotingTiers plugin;

    public ReloadCommand(VotingTiers plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.hasPermission("votingtiers.reload"))
            return true;

        long l = System.currentTimeMillis();
        plugin.reloadConfiguration();
        sender.sendMessage("Reloaded in " + (System.currentTimeMillis() - l) + "ms");
        return true;
    }
}
