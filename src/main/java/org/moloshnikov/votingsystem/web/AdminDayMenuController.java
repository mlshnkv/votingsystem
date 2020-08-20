package org.moloshnikov.votingsystem.web;

import org.moloshnikov.votingsystem.model.DayMenu;
import org.moloshnikov.votingsystem.model.Dish;
import org.moloshnikov.votingsystem.repository.daymenu.DayMenuRepository;
import org.moloshnikov.votingsystem.repository.dish.DishRepository;
import org.moloshnikov.votingsystem.repository.restaurant.RestaurantRepository;
import org.moloshnikov.votingsystem.to.DayMenuTo;
import org.moloshnikov.votingsystem.util.VotingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static org.moloshnikov.votingsystem.util.ValidationUtil.assureIdConsistent;

@RestController
@RequestMapping(value = AdminDayMenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminDayMenuController {
    private final DayMenuRepository dayMenuRepository;
    private final RestaurantRepository restaurantRepository;
    private final DishRepository dishRepository;
    protected final Logger log = LoggerFactory.getLogger(VotingController.class);
    static final String REST_URL = "/admin/daymenu";

    public AdminDayMenuController(DayMenuRepository dayMenuRepository, RestaurantRepository restaurantRepository, DishRepository dishRepository) {
        this.dayMenuRepository = dayMenuRepository;
        this.restaurantRepository = restaurantRepository;
        this.dishRepository = dishRepository;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DayMenu> create(@RequestBody DayMenu dayMenu) {
        dayMenu.setDate(LocalDate.now());
        DayMenu created = dayMenuRepository.save(dayMenu);
        for (Dish menu : created.getDayMenu()) {
            dishRepository.save(menu);
        }
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody DayMenu dayMenu, @PathVariable int id) {
        log.info("update {} with id={}", dayMenu, id);
        assureIdConsistent(dayMenu, id);
        dayMenuRepository.save(dayMenu);
    }

    @GetMapping
    public List<DayMenuTo> getAll() {
        log.info("getAll");
        return VotingUtil.getTos(dayMenuRepository.getAll());
    }

    @GetMapping(value = "/bydate")
    public List<DayMenuTo> getAllByDate(@RequestParam LocalDate date) {
        log.info("getAllByDate");
        return VotingUtil.getTos(dayMenuRepository.getAllByDate(date));
    }
}
