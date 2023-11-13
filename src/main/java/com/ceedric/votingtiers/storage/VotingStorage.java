package com.ceedric.votingtiers.storage;

import java.util.UUID;

public interface VotingStorage {

    void addVote(UUID uuid);

    void setVotes(UUID uuid, int votes);

    int getVotes(UUID uuid);

}
