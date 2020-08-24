package org.moloshnikov.votingsystem.repository.daymenu;

import org.moloshnikov.votingsystem.model.Menu;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Set;

@Transactional(readOnly = true)
public interface CrudMenuRepository extends JpaRepository<Menu, Integer> {

    @EntityGraph(attributePaths = {"votes", "dishes"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT m FROM Menu m JOIN FETCH m.restaurant WHERE m.date=:date")
    Set<Menu> getAllByDate(@Param("date") LocalDate localDate);

    @Modifying
    @Transactional
    @Query("DELETE FROM Menu m WHERE m.id=:id")
    int delete(@Param("id") int id);

    @EntityGraph(attributePaths = {"votes", "dishes"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT m FROM Menu m JOIN FETCH m.restaurant where m.id=:id")
    Menu get(@Param("id") int id);

    @EntityGraph(attributePaths = {"votes", "dishes"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT m FROM Menu m JOIN FETCH m.restaurant")
    Set<Menu> getAll();
}
