package org.moloshnikov.votingsystem.repository;

import org.moloshnikov.votingsystem.model.Vote;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class VoteRepository {
    {
        repo = new HashMap<>();
        count = 0;
    }
    private int count;
    private Map<Integer, Vote> repo;

    public void putVote(Vote vote){
        repo.put(count++, vote);
    }

    public Collection<Vote> getVotes(){
        return repo.values();
    }
}
