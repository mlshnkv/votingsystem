package org.moloshnikov.votingsystem.repository;

import org.moloshnikov.votingsystem.model.DayMenu;

import java.util.List;

public interface DayMenuRepository {
    DayMenu save(DayMenu dayMenu);

    // false if not found
    boolean delete(int id);

    // null if not found
    DayMenu get(int id);


    List<DayMenu> getAll();
}
