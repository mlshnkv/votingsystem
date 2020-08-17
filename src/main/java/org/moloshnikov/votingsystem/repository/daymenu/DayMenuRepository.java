package org.moloshnikov.votingsystem.repository.daymenu;

import org.moloshnikov.votingsystem.model.DayMenu;

import java.time.LocalDate;
import java.util.List;

public interface DayMenuRepository {
    DayMenu save(DayMenu dayMenu);

    // false if not found
    boolean delete(int id);

    // null if not found
    DayMenu get(int id);


    List<DayMenu> getAll();

    List<DayMenu> getAllByDate(LocalDate localDate);
}
