package org.moloshnikov.votingsystem.repository.daymenu;

import org.moloshnikov.votingsystem.model.DayMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CrudMenuRepository extends JpaRepository<DayMenu, Integer> {

    @Query("SELECT d FROM DayMenu d where d.date=:date")
    List<DayMenu> getAllByDate(@Param("date") LocalDate localDate);
}
