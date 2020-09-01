package org.moloshnikov.votingsystem.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.moloshnikov.votingsystem.model.Restaurant;
import org.moloshnikov.votingsystem.util.exception.NotFoundException;
import org.moloshnikov.votingsystem.web.VotingController;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.moloshnikov.votingsystem.TestData.*;


public class RestaurantServiceTest extends AbstractServiceTest {
    private static final String REST_URL = VotingController.REST_URL + '/';
    @Autowired
    private RestaurantService service;

    @Test
    public void delete() {
        service.delete(RESTAURANT_1_ID);
        assertThrows(NotFoundException.class, () -> service.get(RESTAURANT_1_ID));
    }

    @Test
    void deleteNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND));
    }

    @Test
    public void getAll() {
        List<Restaurant> actualRestaurants = service.getAll();
        Assertions.assertEquals(RESTAURANTS, actualRestaurants);
    }

    @Test
    public void get() {
        Restaurant restaurant = service.get(RESTAURANT_1_ID);
        Assertions.assertEquals(RESTAURANT_1, restaurant);
    }

    @Test
    void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND));
    }

    @Test
    void create() throws Exception {
        Restaurant created = service.create(getNewRestaurant());
        int newId = created.id();
        Restaurant newRestaurant = getNewRestaurant();
        newRestaurant.setId(newId);
        Assertions.assertEquals(created, newRestaurant);
        Assertions.assertEquals(service.get(newId), newRestaurant);
    }

    @Test
    void update() throws Exception {
        Restaurant updated = getUpdatedRestaurant();
        service.update(updated);
        Assertions.assertEquals(service.get(RESTAURANT_1_ID), updated);
    }
}