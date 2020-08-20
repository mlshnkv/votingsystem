package org.moloshnikov.votingsystem.repository.daymenu;

import org.moloshnikov.votingsystem.model.DayMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface CrudMenuRepository extends JpaRepository<DayMenu, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM DayMenu d WHERE d.id=:id")
    int delete(@Param("id") int id);

    @Query("SELECT d FROM DayMenu d where d.date=:date")
    List<DayMenu> getAllByDate(@Param("date") LocalDate localDate);

    @Query("SELECT d FROM DayMenu d where d.id=:id")
    DayMenu get(@Param("id") int id);
}
