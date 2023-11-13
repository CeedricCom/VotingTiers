package com.ceedric.votingtiers.object;

import com.ceedric.votingtiers.VotingTiers;
import com.ceedric.votingtiers.rewards.Reward;
import com.ceedric.votingtiers.storage.VotingStorage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class TierManager {

    private final Set<Tier> tiers = new HashSet<>();
    private final VotingTiers plugin;

    public TierManager(VotingTiers plugin) {
        this.plugin = plugin;
    }

    public void handleVote(UUID uuid) {
        VotingStorage storage = plugin.getVotingStorage();

        int votes = storage.getVotes(uuid) + 1;

        Tier tier = getTier(votes);

        if (tier == null)
            return;

        handleRewards(tier, uuid, votes);
    }

    public void addTier(Tier tier) {
        tiers.add(tier);
    }

    public Set<Tier> getTiers() {
        return new HashSet<>(tiers);
    }

    public void clearTiers() {
        tiers.clear();
    }

    public Tier getTier(int votes) {
        return getTiers().stream()
                .filter(tier -> tier.getRequiredVotes() <= votes)
                .max(Comparator.comparingInt(Tier::getRequiredVotes)).orElse(null);
    }

    private void handleRewards(Tier tier, UUID uuid, int votes) {
        Player player = Bukkit.getPlayer(uuid);

        if (player == null || !player.isOnline())
            return;

        for (Reward reward : tier.getRewards())
            reward.executeReward(player);


        if (votes % tier.getCumulativeRequiredVotes() == 0) {
            for (Reward reward : tier.getCumulativeVotesRewards())
                reward.executeReward(player);
        }

        if (votes == tier.getRequiredVotes()) {
            for (Reward reward : tier.getUpgradeRewards())
                reward.executeReward(player);
        }
    }
}
