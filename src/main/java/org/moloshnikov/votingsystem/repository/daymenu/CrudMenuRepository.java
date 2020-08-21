package org.moloshnikov.votingsystem.repository.daymenu;

import org.moloshnikov.votingsystem.model.DayMenu;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.QueryHint;
import java.time.LocalDate;
import java.util.List;

public interface CrudMenuRepository extends JpaRepository<DayMenu, Integer> {

    @EntityGraph(attributePaths = {"votes", "dishes"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT d FROM DayMenu d JOIN FETCH d.restaurant WHERE d.date=:date")
    List<DayMenu> getAllByDate(@Param("date") LocalDate localDate);

    @Modifying
    @Transactional
    @Query("DELETE FROM DayMenu d WHERE d.id=:id")
    int delete(@Param("id") int id);

    @EntityGraph(attributePaths = {"votes"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT d FROM DayMenu d JOIN FETCH d.restaurant where d.id=:id")
    DayMenu get(@Param("id") int id);

    @EntityGraph(attributePaths = {"votes"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT d FROM DayMenu d JOIN FETCH d.restaurant")
    List<DayMenu> getAll();
}
