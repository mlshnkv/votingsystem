package org.moloshnikov.votingsystem.to;

import org.moloshnikov.votingsystem.model.Dish;

import java.util.List;

public class RestaurantTo {

    private final String name;

    private final List<Dish> menu;

    private int votes;

    public RestaurantTo(String name, List<Dish> menu, int votes) {
        this.name = name;
        this.menu = menu;
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "Ресторан " + name + " набрал: " + votes + " голосов";
    }
}
