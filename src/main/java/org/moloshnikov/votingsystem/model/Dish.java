package org.moloshnikov.votingsystem.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "dishes")
public class Dish extends AbstractNamedEntity {
    @Column(name = "price", nullable = false)
    @Range(min = 10, max = 5000)
    @NotNull
    private int price;

    public Dish() {

    }

    public Dish(@Range(min = 10, max = 5000) @NotNull int price) {
        this.price = price;
    }

    public Dish(Integer id, String name, @Range(min = 10, max = 5000) @NotNull int price) {
        super(id, name);
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return name +
                ", цена: " + price;
    }
}
