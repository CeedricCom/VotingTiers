package com.ceedric.votingtiers;

import com.ceedric.votingtiers.object.Tier;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;
import java.util.Set;

public class TierPlaceholders extends PlaceholderExpansion {

    private final VotingTiers plugin;

    public TierPlaceholders(VotingTiers plugin) {
        this.plugin = plugin;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "votingtiers";
    }

    @Override
    public @NotNull String getAuthor() {
        return "cedric";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }

    @Override
    public @Nullable String onRequest(OfflinePlayer offlinePlayer, @NotNull String params) {
        switch (params.toLowerCase()) {
            case "tier": {
                int votes = plugin.getVotingStorage().getVotes(offlinePlayer.getUniqueId());
                return plugin.getTierManager().getTier(votes).getDisplayName();
            }
            case "votes": {
                return plugin.getVotingStorage().getVotes(offlinePlayer.getUniqueId()) + "";
            }
            case "nexttier": {
                Set<Tier> tiers = plugin.getTierManager().getTiers();
                int votes = plugin.getVotingStorage().getVotes(offlinePlayer.getUniqueId());
                Tier tier = tiers.stream().filter(tier1 -> tier1.getRequiredVotes() >= votes)
                        .min(Comparator.comparingInt(Tier::getRequiredVotes))
                        .orElse(null);
                if (tier != null)
                    return (tier.getRequiredVotes() - votes) + "";
                else
                    return "";
            }
            default: {
                return "";
            }
        }
    }
}
