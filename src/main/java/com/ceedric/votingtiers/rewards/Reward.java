package com.ceedric.votingtiers.rewards;

import org.bukkit.entity.Player;

import java.util.UUID;

public interface Reward {

    void executeReward(Player player);

}
