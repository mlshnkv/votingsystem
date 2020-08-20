package org.moloshnikov.votingsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractNamedEntity {

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant", cascade = CascadeType.REMOVE)
    //, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonManagedReference(value = "restaurant-dayMenu")
    private Set<DayMenu> menus;

    public Restaurant() {
    }

    public Restaurant(String name) {
        super(null, name);
    }

    public Set<DayMenu> getMenus() {
        return menus;
    }

    public void setMenus(Set<DayMenu> menus) {
        this.menus = menus;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                '}';
    }
}
