package org.moloshnikov.votingsystem.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractNamedEntity {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant", cascade = CascadeType.REMOVE)
    //, cascade = CascadeType.REMOVE, orphanRemoval = true)
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
