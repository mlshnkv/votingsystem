package org.moloshnikov.votingsystem.repository.menu;

import org.moloshnikov.votingsystem.model.Menu;
import org.moloshnikov.votingsystem.repository.restaurant.CrudRestaurantRepository;
import org.moloshnikov.votingsystem.repository.restaurant.RestaurantRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public class MenuRepositoryImpl implements MenuRepository {
    private final CrudMenuRepository crudMenuRepository;
    private final CrudRestaurantRepository crudRestaurantRepository;

    public MenuRepositoryImpl(CrudMenuRepository crudMenuRepository, CrudRestaurantRepository crudRestaurantRepository) {
        this.crudMenuRepository = crudMenuRepository;
        this.crudRestaurantRepository = crudRestaurantRepository;
    }

    @Override
    @Transactional
    public Menu save(Menu menu, int restaurantId) {
        if (!menu.isNew() && get(restaurantId, menu.getId()) == null) {
            return null;
        }
        menu.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
        return crudMenuRepository.save(menu);
    }

    @Override
    public boolean delete(int restaurantId, int menuId) {
        return crudMenuRepository.delete(restaurantId, menuId) != 0;
    }

    @Override
    public Menu get(int restaurantId, int id) {
        return crudMenuRepository.get(restaurantId, id);
    }

    @Override
    public List<Menu> getAll() {
        return crudMenuRepository.getAll();
    }

    @Override
    public List<Menu> getAllByDate(LocalDate localDate) {
        return crudMenuRepository.getAllByDate(localDate);
    }
}