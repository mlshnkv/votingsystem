package org.moloshnikov.votingsystem.repository;

import org.moloshnikov.votingsystem.model.Dish;

import java.util.List;

public interface DishRepository {
    Dish save(Dish dish);

    // false if not found
    boolean delete(int id);

    // null if not found
    Dish get(int id);

    List<Dish> getAll();
}
