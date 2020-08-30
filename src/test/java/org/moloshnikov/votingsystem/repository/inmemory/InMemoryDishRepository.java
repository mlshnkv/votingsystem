package org.moloshnikov.votingsystem.repository.inmemory;

import org.moloshnikov.votingsystem.model.Dish;
import org.moloshnikov.votingsystem.repository.dish.DishRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InMemoryDishRepository extends InMemoryBaseRepository<Dish> implements DishRepository {
    @Override
    public List<Dish> getAll() {
        return null;
    }
}
