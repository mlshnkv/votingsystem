package org.moloshnikov.votingsystem.to;

import org.moloshnikov.votingsystem.model.Dish;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class RestaurantTo extends BaseTo implements Serializable {
    private String name;
    private List<Dish> menu;

    public RestaurantTo() {
    }

    public RestaurantTo(int id, String name) {
        super(id);
        this.name = name;
    }

    public RestaurantTo(int id, String name, List<Dish> menu) {
        super(id);
        this.name = name;
        this.menu = menu;
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

    @Override
    public String toString() {
        return "RestaurantTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", menu=" + menu +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestaurantTo that = (RestaurantTo) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(menu, that.menu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, menu);
    }
}