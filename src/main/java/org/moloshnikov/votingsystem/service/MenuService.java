package org.moloshnikov.votingsystem.service;

import org.moloshnikov.votingsystem.model.Menu;
import org.moloshnikov.votingsystem.repository.MenuRepository;
import org.moloshnikov.votingsystem.repository.RestaurantRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

import static org.moloshnikov.votingsystem.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MenuService {
    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;

    public MenuService(MenuRepository menuRepository, RestaurantRepository restaurantRepository) {
        this.menuRepository = menuRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public List<Menu> getMenusByDate(LocalDate date) {
        return menuRepository.getAllByDate(date);
    }

    public Menu getMenuById(int restaurantId, int menuId) {
        return checkNotFoundWithId(menuRepository.get(restaurantId, menuId), menuId);
    }

    public Menu save(int restaurantId, Menu menu) {
        Assert.notNull(menu, "menu must not be null");
        if (!menu.isNew() && menuRepository.get(restaurantId, menu.getId()) == null) {
            return null;
        }
        menu.setRestaurant(restaurantRepository.getOne(restaurantId));
        return menuRepository.save(menu);
    }

    public void deleteMenu(int restaurantId, int menuId) {
        checkNotFoundWithId(menuRepository.delete(restaurantId, menuId) != 0, menuId);
    }
}