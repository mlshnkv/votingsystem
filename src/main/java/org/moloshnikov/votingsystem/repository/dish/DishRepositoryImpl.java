package org.moloshnikov.votingsystem.repository.dish;

import org.moloshnikov.votingsystem.model.Dish;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class DishRepositoryImpl implements DishRepository {
    private final CrudDishRepository crudDishRepository;

    public DishRepositoryImpl(CrudDishRepository crudDishRepository) {
        this.crudDishRepository = crudDishRepository;
    }

    @Override
    @Transactional
    public Dish save(Dish dish) {
        return crudDishRepository.save(dish);
    }

    @Override
    public boolean delete(int id) {
        return crudDishRepository.delete(id) != 0;
    }

    @Override
    public Dish get(int id) {
        return crudDishRepository.getOne(id);
    }

    @Override
    public List<Dish> getAll() {
        return crudDishRepository.findAll();
    }
}
