package org.moloshnikov.votingsystem.repository.vote;

import org.moloshnikov.votingsystem.model.Vote;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DataJpaVoteRepository implements VoteRepository {
    private final CrudVotesRepository crudVotesRepository;


    public DataJpaVoteRepository(CrudVotesRepository crudVotesRepository) {
        this.crudVotesRepository = crudVotesRepository;
    }

    @Override
    public Vote save(Vote vote) {
        return crudVotesRepository.save(vote);
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
    public Vote getByUserIdDate(int userId, LocalDate localDate) {
        Vote vote = crudVotesRepository.getByUserIdDate(userId, localDate);
        return vote;
    }

    @Override
    public List<Vote> getAllByDay(LocalDate localDate) {
        return crudVotesRepository.getAllByDay(localDate);
    }

    @Override
    public List<Vote> getAll() {
        return null;
    }
}
