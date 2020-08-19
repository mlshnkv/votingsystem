package org.moloshnikov.votingsystem.repository.vote;

import org.moloshnikov.votingsystem.model.Vote;

import java.time.LocalDate;
import java.util.List;

public interface VoteRepository {

    Vote save(Vote vote);

    // false if not found
    boolean delete(int id);

    // null if not found
    Vote get(int id);

    Vote getByUserIdDate(int userId, LocalDate localDate);

    List<Vote> getAllByDay(LocalDate localDate);

    List<Vote> getAll();
}
