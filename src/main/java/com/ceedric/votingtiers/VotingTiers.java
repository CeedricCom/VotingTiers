package com.ceedric.votingtiers;

import com.ceedric.votingtiers.command.EvaluateRewardCommand;
import com.ceedric.votingtiers.command.ReloadCommand;
import com.ceedric.votingtiers.config.ConfigLoader;
import com.ceedric.votingtiers.object.TierManager;
import com.ceedric.votingtiers.storage.SuperbVoteStorage;
import com.ceedric.votingtiers.storage.VotingStorage;
import org.bukkit.plugin.java.JavaPlugin;

public final class VotingTiers extends JavaPlugin {

    private TierManager tierManager;
    private VotingStorage votingStorage = null;
    private ConfigLoader configLoader;

    @Override
    public void onEnable() {
        votingStorage = new SuperbVoteStorage();
        tierManager = new TierManager(this);
        configLoader = new ConfigLoader(this);
        new TierPlaceholders(this).register();

        saveDefaultConfig();
        configLoader.loadConfig();

        getServer().getPluginManager().registerEvents(new VotingListener(this), this);

        getCommand("reloadvotingtiers").setExecutor(new ReloadCommand(this));
        getCommand("evaluatevote").setExecutor(new EvaluateRewardCommand(this));
    }

    public VotingStorage getVotingStorage() {
        return votingStorage;
    }

    public TierManager getTierManager() {
        return tierManager;
    }

    public void reloadConfiguration() {
        reloadConfig();
        tierManager.clearTiers();
        configLoader.loadConfig();
    }
}
