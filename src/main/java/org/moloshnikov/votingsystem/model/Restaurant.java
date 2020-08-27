package org.moloshnikov.votingsystem.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractNamedEntity {


//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant", cascade = CascadeType.REMOVE)
////    @JsonManagedReference(value = "restaurant-menu")
//    private List<Menu> menus;

    public Restaurant() {
    }

    public Restaurant(String name) {
        super(null, name);
    }

//    public List<Menu> getMenus() {
//        return menus;
//    }
//
//    public void setMenus(List<Menu> menus) {
//        this.menus = menus;
//    }

    @Override
    public String toString() {
        return name;
    }
}
