package org.moloshnikov.votingsystem.repository.daymenu;

import org.moloshnikov.votingsystem.model.Menu;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMenuRepository extends JpaRepository<Menu, Integer> {

    @EntityGraph(attributePaths = {"dishes"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT m FROM Menu m JOIN FETCH m.restaurant WHERE m.date=:date")
    List<Menu> getAllByDate(@Param("date") LocalDate date);

    @EntityGraph(attributePaths = {"votes", "dishes"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT m FROM Menu m JOIN FETCH m.restaurant")
    List<Menu> getAll();

    @EntityGraph(attributePaths = {"dishes"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT m FROM Menu m JOIN FETCH m.restaurant WHERE m.id=:id AND m.restaurant.id=:restaurantId")
    Menu get(@Param("restaurantId") int restaurantId, @Param("id") int id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Menu m WHERE m.id=:menuId AND m.restaurant.id=:restaurantId")
    int delete(@Param("restaurantId") int restaurantId, @Param("menuId") int menuId);
}
