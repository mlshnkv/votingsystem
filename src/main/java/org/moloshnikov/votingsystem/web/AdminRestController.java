package org.moloshnikov.votingsystem.web;

import org.moloshnikov.votingsystem.model.Restaurant;
import org.moloshnikov.votingsystem.repository.RestaurantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import java.util.List;

import static org.moloshnikov.votingsystem.util.ValidationUtil.assureIdConsistent;

@Controller
public class AdminRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private final RestaurantRepository restaurantRepository;

    public AdminRestController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<Restaurant> getAll() {
        log.info("getAll");
        return restaurantRepository.getAll();
    }

    public Restaurant get(int id) {
        log.info("get {}", id);
        return restaurantRepository.get(id);
    }

    public Restaurant create(Restaurant restaurant) {
        log.info("create {}", restaurant);
//        checkNew(user);
        return restaurantRepository.save(restaurant);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        restaurantRepository.delete(id);
    }

    public void update(Restaurant restaurant, int id) {
        log.info("update {} with id={}", restaurant, id);
        assureIdConsistent(restaurant, id);
        restaurantRepository.save(restaurant);
    }

//    public User getWithMeals(int id) {
//        log.info("getWithMeals {}", id);
//        return service.getWithMeals(id);
//    }
}
