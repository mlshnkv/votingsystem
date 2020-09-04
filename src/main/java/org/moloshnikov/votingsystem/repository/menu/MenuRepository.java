package org.moloshnikov.votingsystem.repository.menu;

import org.moloshnikov.votingsystem.model.Menu;

import java.time.LocalDate;
import java.util.List;

public interface MenuRepository {
    Menu save(Menu menu, int restaurantId);

    boolean delete(int restaurantId, int menuId);

    Menu get(int restaurantId, int menuId);

    List<Menu> getAll();

    List<Menu> getAllByDate(LocalDate localDate);
}