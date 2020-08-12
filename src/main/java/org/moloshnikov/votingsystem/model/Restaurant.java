package org.moloshnikov.votingsystem.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Restaurant extends AbstractNamedEntity{
    {
        menu = new ArrayList<Dish>();
    }

    private List<Dish> menu;
    private Set<DayMenu> menus;

    public Restaurant(String name) {
        this.name = name;
    }

    public List<Dish> getMenu() {
        return menu;
    }

    public void addDishToMenu(Dish dish) {
        menu.add(dish);
    }

    public void setMenu(List<Dish> menu) {
        this.menu = menu;
    }

    public Set<DayMenu> getMenus() {
        return menus;
    }

    public void setMenus(Set<DayMenu> menus) {
        this.menus = menus;
    }

    @Override
    public String toString() {
        return "Название ресторана: " +
                name + "\n" +
                "Меню: " + menu;
    }
}
