package org.moloshnikov.votingsystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "dishes")
public class Dish extends AbstractNamedEntity{
    @Column(name = "price", nullable = false)
    @Range(min = 10, max = 5000)
    @NotNull
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "day_menu_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
//    @NotNull
    private DayMenu dayMenu;

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

    public DayMenu getDayMenu() {
        return dayMenu;
    }

    public void setDayMenu(DayMenu dayMenu) {
        this.dayMenu = dayMenu;
    }

    @Override
    public String toString() {
        return name +
                ", цена: " + price;
    }
}
