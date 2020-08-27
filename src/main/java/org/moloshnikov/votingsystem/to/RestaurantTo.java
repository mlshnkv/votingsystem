package org.moloshnikov.votingsystem.to;

import org.moloshnikov.votingsystem.model.Dish;

import java.util.List;

public class RestaurantTo {
    private int id;
    //    private final LocalDate date;
    private String name;
    private List<Dish> menu;
    private int votes = 0;

    public RestaurantTo() {
    }

    public RestaurantTo(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public RestaurantTo(int id, String name, List<Dish> menu, int votes) {
        this.id = id;
        this.name = name;
        this.menu = menu;
        this.votes = votes;
    }

//    public LocalDate getDate() {
//        return date;
//    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Dish> getMenu() {
        return menu;
    }

    public void setMenu(List<Dish> menu) {
        this.menu = menu;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "RestaurantTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", menu=" + menu +
                ", votes=" + votes +
                '}';
    }
}
