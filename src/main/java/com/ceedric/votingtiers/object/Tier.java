package com.ceedric.votingtiers.object;

import com.ceedric.votingtiers.rewards.Reward;

import java.util.ArrayList;
import java.util.List;

public class Tier {

    private final int requiredVotes;
    private final int cumulativeVotes;
    private final String displayName;
    private final List<Reward> rewards = new ArrayList<>();
    private final List<Reward> cumulativeVotesRewards = new ArrayList<>();
    private final List<Reward> upgradeRewards = new ArrayList<>();

    public Tier(String displayName, int requiredVotes, int cumulativeVotes) {
        this.requiredVotes = requiredVotes;
        this.cumulativeVotes = cumulativeVotes;
        this.displayName = displayName;
    }

    public void addReward(Reward reward) {
        rewards.add(reward);
    }

    public void addCumulativeVotingReward(Reward reward) {
        cumulativeVotesRewards.add(reward);
    }

    public void addUpgradeReward(Reward reward) {
        upgradeRewards.add(reward);
    }

    public List<Reward> getRewards() {
        return new ArrayList<>(rewards);
    }

    public List<Reward> getCumulativeVotesRewards() {
        return new ArrayList<>(cumulativeVotesRewards);
    }

    public List<Reward> getUpgradeRewards() {
        return new ArrayList<>(upgradeRewards);
    }

    public int getRequiredVotes() {
        return requiredVotes;
    }

    public int getCumulativeRequiredVotes() {
        return cumulativeVotes;
    }

    public String getDisplayName() {
        return displayName;
    }
}
