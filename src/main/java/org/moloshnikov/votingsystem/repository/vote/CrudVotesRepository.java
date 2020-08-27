package org.moloshnikov.votingsystem.repository.vote;

import org.moloshnikov.votingsystem.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudVotesRepository extends JpaRepository<Vote, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Vote v WHERE v.user.id=:userId AND v.localDate=:date")
    int delete(@Param("userId") int userId, @Param("date")LocalDate date);


    @Query("SELECT v FROM Vote v JOIN FETCH v.restaurant where v.localDate=:date")
    List<Vote> getAllByDay(@Param("date") LocalDate localDate);

    @Query("SELECT v FROM Vote v WHERE v.user.id=:userId AND v.localDate=:date")
    Vote getByUserIdDate(@Param("userId") int userId, @Param("date") LocalDate date);

}
