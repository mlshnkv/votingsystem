package org.moloshnikov.votingsystem.repository.menu;

import org.moloshnikov.votingsystem.model.Menu;

import java.time.LocalDate;
import java.util.List;

public interface MenuRepository {
    Menu save(Menu menu, int restaurantId);

    // false if not found
    boolean delete(int restaurantId, int menuId);

    // null if not found
    Menu get(int restaurantId, int menuId);

    List<Menu> getAll();

    List<Menu> getAllByDate(LocalDate localDate);
}
