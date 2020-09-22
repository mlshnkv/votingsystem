package org.moloshnikov.votingsystem.repository;

import org.moloshnikov.votingsystem.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer> {
    @Modifying
    @Query("DELETE FROM Vote v WHERE v.user.id=:userId AND v.localDate=:date")
    int delete(@Param("userId") int userId, @Param("date") LocalDate date);

    @Query("SELECT v FROM Vote v WHERE v.user.id=:userId AND v.localDate=:date")
    Vote getByUserIdDate(@Param("userId") int userId, @Param("date") LocalDate date);
}