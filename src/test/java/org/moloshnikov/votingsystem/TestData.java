package org.moloshnikov.votingsystem;

import org.moloshnikov.votingsystem.model.*;
import org.moloshnikov.votingsystem.to.RestaurantTo;
import org.moloshnikov.votingsystem.util.VotingUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.moloshnikov.votingsystem.model.AbstractBaseEntity.START_SEQ;

public class TestData {
    public static TestMatcher<Restaurant> RESTAURANT_MATCHER = TestMatcher.usingEqualsAssertions(Restaurant.class);
    public static TestMatcher<RestaurantTo> RESTAURANT_TO_MATCHER = TestMatcher.usingEqualsAssertions(RestaurantTo.class);
    public static TestMatcher<Menu> MENU_MATCHER = TestMatcher.usingEqualsAssertions(Menu.class);

    public static final int NOT_FOUND = 10;
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;
    public static final User USER = new User(USER_ID, "User", "user@yandex.ru", "password", Role.USER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", Role.ADMIN, Role.USER);

    public static final int RESTAURANT_1_ID = START_SEQ + 2;
    public static final int RESTAURANT_2_ID = START_SEQ + 3;

    public static final Restaurant RESTAURANT_1 = new Restaurant(RESTAURANT_1_ID, "Ракушка");
    public static final Restaurant RESTAURANT_2 = new Restaurant(RESTAURANT_2_ID, "Уголек");
    public static final Restaurant RESTAURANT_1_WITHOUT_NAME = new Restaurant(RESTAURANT_1_ID, null);
    public static final List<Restaurant> RESTAURANTS = Arrays.asList(RESTAURANT_1, RESTAURANT_2);


    public static final LocalDate TODAY = LocalDate.now();
    public static final LocalDate YESTERDAY = TODAY.minusDays(1);

    public static final int DISH_1_ID = START_SEQ + 8;
    public static final int DISH_2_ID = START_SEQ + 9;
    public static final int DISH_3_ID = START_SEQ + 10;
    public static final int DISH_4_ID = START_SEQ + 11;
    public static final int DISH_5_ID = START_SEQ + 12;
    public static final int DISH_6_ID = START_SEQ + 13;
    public static final int DISH_7_ID = START_SEQ + 14;
    public static final int DISH_8_ID = START_SEQ + 15;
    public static final int DISH_9_ID = START_SEQ + 16;
    public static final int DISH_10_ID = START_SEQ + 17;
    public static final int DISH_11_ID = START_SEQ + 18;
    public static final int DISH_12_ID = START_SEQ + 19;

    public static final Dish DISH_1 = new Dish(DISH_1_ID, "Рыба", 100);
    public static final Dish DISH_2 = new Dish(DISH_2_ID, "Картофель", 80);
    public static final Dish DISH_3 = new Dish(DISH_3_ID, "Хлеб", 5);
    public static final Dish DISH_4 = new Dish(DISH_4_ID, "Мойва", 35);
    public static final Dish DISH_5 = new Dish(DISH_5_ID, "Скумбрия", 45);
    public static final Dish DISH_6 = new Dish(DISH_6_ID, "Борщ", 55);
    public static final Dish DISH_7 = new Dish(DISH_7_ID, "Икра", 600);
    public static final Dish DISH_8 = new Dish(DISH_8_ID, "Кабачек", 70);
    public static final Dish DISH_9 = new Dish(DISH_9_ID, "Орех", 35);
    public static final Dish DISH_10 = new Dish(DISH_10_ID, "Суп", 60);
    public static final Dish DISH_11 = new Dish(DISH_11_ID, "Блины", 115);
    public static final Dish DISH_12 = new Dish(DISH_12_ID, "Оладьи", 120);

    public static final int MENU_1_ID = START_SEQ + 4;
    public static final int MENU_2_ID = START_SEQ + 5;
    public static final int MENU_3_ID = START_SEQ + 6;
    public static final int MENU_4_ID = START_SEQ + 7;

    public static final Menu MENU_1 = new Menu(MENU_1_ID, YESTERDAY, RESTAURANT_1, DISH_1, DISH_2, DISH_3);
    public static final Menu MENU_2 = new Menu(MENU_2_ID, YESTERDAY, RESTAURANT_2, DISH_4, DISH_5, DISH_6);
    public static final Menu MENU_3 = new Menu(MENU_3_ID, TODAY, RESTAURANT_1, DISH_7, DISH_8, DISH_9);
    public static final Menu MENU_4 = new Menu(MENU_4_ID, TODAY, RESTAURANT_2, DISH_10, DISH_11, DISH_12);
    public static final List<Menu> MENUS_TODAY = Arrays.asList(MENU_3, MENU_4);

    public static final int VOTE_1_ID = START_SEQ + 20;
    public static final int VOTE_2_ID = START_SEQ + 21;
    public static final int VOTE_3_ID = START_SEQ + 22;
    public static final int VOTE_4_ID = START_SEQ + 23;

    public static final LocalTime TIME = LocalTime.of(10, 0, 0);

    public static final Vote VOTE_1 = new Vote(VOTE_1_ID, YESTERDAY, TIME, RESTAURANT_1, USER);
    public static final Vote VOTE_2 = new Vote(VOTE_2_ID, YESTERDAY, TIME, RESTAURANT_1, ADMIN);
    public static final Vote VOTE_3 = new Vote(VOTE_3_ID, TODAY, TIME, RESTAURANT_2, USER);
    public static final Vote VOTE_4 = new Vote(VOTE_4_ID, TODAY, TIME, RESTAURANT_2, ADMIN);

    public static final List<Vote> VOTES_TODAY = Arrays.asList(VOTE_3, VOTE_4);

    public static final List<RestaurantTo> RESTAURANT_TOS_TODAY = VotingUtil.getTos(MENUS_TODAY);

    public static Restaurant getNewRestaurant() {
        return new Restaurant(null, "Тестовый ресторан");
    }

    public static Restaurant getUpdatedRestaurant() {
        Restaurant updated = new Restaurant(RESTAURANT_1);
        updated.setName("UpdatedName");
        return updated;
    }

    public static Menu getNewMenu() {
        return new Menu(null, TODAY.plusDays(1), null, new Dish(null, "Test dish 1", 234), new Dish(null, "Test dish 2", 548));
    }

    public static Menu getUpdatedMenu() {
        Menu updated = new Menu(MENU_1);
        updated.setDishes(Arrays.asList(DISH_1, DISH_2));
        return updated;
    }
}
