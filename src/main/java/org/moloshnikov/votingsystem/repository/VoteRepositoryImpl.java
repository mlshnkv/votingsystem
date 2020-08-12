package org.moloshnikov.votingsystem.repository;

import org.moloshnikov.votingsystem.model.Vote;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VoteRepositoryImpl implements VoteRepository{
    {
        repo = new HashMap<>();
        count = 0;
    }
    private int count;
    private Map<Integer, Vote> repo;

    public Vote save(Vote vote){
        repo.put(count++, vote);
        return vote;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Vote get(int id) {
        return null;
    }

    @Override
    public List<Vote> getAll() {
        return null;
    }

    public Collection<Vote> getVotes(){
        return repo.values();
    }
}
