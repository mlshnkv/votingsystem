package org.moloshnikov.votingsystem.repository.daymenu;

import org.moloshnikov.votingsystem.model.Menu;

import java.time.LocalDate;
import java.util.Set;

public interface MenuRepository {
    Menu save(Menu menu);

    // false if not found
    boolean delete(int id);

    // null if not found
    Menu get(int id);


    Set<Menu> getAll();

    Set<Menu> getAllByDate(LocalDate localDate);
}
