package com.ceedric.votingtiers.storage;

import io.minimum.minecraft.superbvote.SuperbVote;

import java.util.UUID;

public class SuperbVoteStorage implements VotingStorage {

    @Override
    public void addVote(UUID uuid) {
        // SuperbVote already handles that
    }

    @Override
    public void setVotes(UUID uuid, int votes) {
        SuperbVote.getPlugin().getVoteStorage().setVotes(uuid, votes);
    }

    @Override
    public int getVotes(UUID uuid) {
        return SuperbVote.getPlugin().getVoteStorage().getVotes(uuid).getVotes();
    }
}
