package com.ceedric.votingtiers.config;

import com.ceedric.votingtiers.VotingTiers;
import com.ceedric.votingtiers.object.Tier;
import com.ceedric.votingtiers.rewards.BroadcastReward;
import com.ceedric.votingtiers.rewards.CommandReward;
import com.ceedric.votingtiers.rewards.EmptyReward;
import com.ceedric.votingtiers.rewards.ItemReward;
import com.ceedric.votingtiers.rewards.MessageReward;
import com.ceedric.votingtiers.rewards.Reward;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ConfigLoader {

    private VotingTiers plugin;

    public ConfigLoader(VotingTiers plugin) {
        this.plugin = plugin;
    }

    public void loadConfig() {
        ConfigurationSection section = plugin.getConfig().getConfigurationSection("tiers");

        for (String key : section.getKeys(false)) {
            ConfigurationSection currentTier = section.getConfigurationSection(key);

            String displayName = ChatColor.translateAlternateColorCodes('&', currentTier.getString("display-name"));
            int requiredVotes = currentTier.getInt("required-votes");

            ConfigurationSection cumulativeVotes = currentTier.getConfigurationSection("every-cumulative-votes");
            int requiredCumulativeVotes = cumulativeVotes.getInt("amount");

            List<Reward> cumulativeVoteRewards = parseRewards(cumulativeVotes.getConfigurationSection("rewards"));
            List<Reward> voteRewards = parseRewards(currentTier.getConfigurationSection("per-vote-rewards"));
            List<Reward> immediateRewards = parseRewards(currentTier.getConfigurationSection("immediate-upgrade-rewards"));

            Tier tier = new Tier(displayName, requiredVotes, requiredCumulativeVotes);

            for (Reward reward : cumulativeVoteRewards)
                tier.addCumulativeVotingReward(reward);

            for (Reward reward : voteRewards)
                tier.addReward(reward);

            for (Reward reward : immediateRewards)
                tier.addUpgradeReward(reward);

            plugin.getTierManager().addTier(tier);
        }
    }

    private List<Reward> parseRewards(ConfigurationSection rewardSection) {
        List<Reward> rewards = new ArrayList<>();
        for (String key : rewardSection.getKeys(false)) {

            ConfigurationSection currentItem = rewardSection.getConfigurationSection(key);
            String type = currentItem.getString("type");

            Reward reward = new EmptyReward();
            switch (type.toLowerCase()) {
                case "item": {
                    ItemStack itemStack = currentItem.getItemStack("itemstack");
                    reward = new ItemReward(itemStack);
                    break;
                }

                case "command": {
                    List<String> commands = currentItem.getStringList("commands");
                    reward = new CommandReward(plugin, commands);
                    break;
                }

                case "messages": {
                    List<String> messages = currentItem.getStringList("messages");
                    reward = new MessageReward(messages);
                    break;
                }

                case "broadcast": {
                    List<String> messages = currentItem.getStringList("messages");
                    reward = new BroadcastReward(messages);
                    break;
                }

            }

            rewards.add(reward);
        }

        return rewards;
    }

}
















