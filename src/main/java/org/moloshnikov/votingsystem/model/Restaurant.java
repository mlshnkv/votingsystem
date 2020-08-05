package org.moloshnikov.votingsystem.model;

import java.util.ArrayList;
import java.util.List;

public class Restaurant extends AbstractNamedEntity{
    {
        menu = new ArrayList<Dish>();
    }

    private List<Dish> menu;

    public Restaurant(String name) {
        this.name = name;
    }

    public List<Dish> getMenu() {
        return menu;
    }

    public void addDishToMenu(Dish dish) {
        menu.add(dish);
    }

    @Override
    public String toString() {
        return "Название ресторана: " +
                name + "\n" +
                "Меню: " + menu;
    }
}
