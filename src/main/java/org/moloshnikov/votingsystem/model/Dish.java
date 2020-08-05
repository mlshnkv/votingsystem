package org.moloshnikov.votingsystem.model;

public class Dish extends AbstractNamedEntity{
    private int price;
    private Restaurant restaurant;

    public Dish(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return name +
                ", цена: " + price;
    }
}
