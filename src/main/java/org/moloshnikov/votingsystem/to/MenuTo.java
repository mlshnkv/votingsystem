package org.moloshnikov.votingsystem.to;

import org.moloshnikov.votingsystem.model.Dish;
import org.moloshnikov.votingsystem.model.Restaurant;

import java.time.LocalDate;
import java.util.Set;

public class MenuTo {
    private final int id;
    private final LocalDate date;
    private final Restaurant restaurant;
    private final Set<Dish> menu;
    private final int votes;

    public MenuTo(int id, LocalDate date, Restaurant restaurant, Set<Dish> menu, int votes) {
        this.id = id;
        this.date = date;
        this.restaurant = restaurant;
        this.menu = menu;
        this.votes = votes;
    }

    public LocalDate getDate() {
        return date;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public Set<Dish> getMenu() {
        return menu;
    }

    public int getVotes() {
        return votes;
    }

    @Override
    public String toString() {
        return "Ресторан: " + restaurant + ";\n"
                + "Меню дня: " + "\n"
                + menu + "\n"
                + "Количаство голосов: " + votes;
    }
}
