package org.moloshnikov.votingsystem.service;

import org.moloshnikov.votingsystem.model.Menu;
import org.moloshnikov.votingsystem.repository.menu.MenuRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

import static org.moloshnikov.votingsystem.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MenuService {
    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public List<Menu> getMenusByDate(LocalDate date) {
        return menuRepository.getAllByDate(date);
    }

    public Menu getMenuById(int restaurantId, int menuId) {
        return checkNotFoundWithId(menuRepository.get(restaurantId, menuId), menuId);
    }

    @CacheEvict(value = {"restaurants", "votes"}, allEntries = true)
    public Menu createMenu(int restaurantId, Menu menu) {
        Assert.notNull(menu, "menu must not be null");
        return menuRepository.save(menu, restaurantId);
    }

    @CacheEvict(value = {"restaurants", "votes"}, allEntries = true)
    public void deleteMenu(int restaurantId, int menuId) {
        checkNotFoundWithId(menuRepository.delete(restaurantId, menuId), menuId);
    }

    @CacheEvict(value = {"restaurants", "votes"}, allEntries = true)
    public void updateMenu(int restaurantId, Menu menu) {
        Assert.notNull(menu, "menu must not be null");
        checkNotFoundWithId(menuRepository.save(menu, restaurantId), menu.getId());
    }
}