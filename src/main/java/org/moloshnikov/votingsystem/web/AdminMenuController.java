package org.moloshnikov.votingsystem.web;

import org.moloshnikov.votingsystem.model.Menu;
import org.moloshnikov.votingsystem.repository.daymenu.MenuRepository;
import org.moloshnikov.votingsystem.to.MenuTo;
import org.moloshnikov.votingsystem.util.ValidationUtil;
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
import java.util.Set;

import static org.moloshnikov.votingsystem.util.ValidationUtil.assureIdConsistent;

@RestController
@RequestMapping(value = AdminMenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminMenuController {
    private final MenuRepository menuRepository;
    protected final Logger log = LoggerFactory.getLogger(VotingController.class);
    static final String REST_URL = "/admin/menu";

    public AdminMenuController(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> create(@RequestBody Menu menu) {
        ValidationUtil.checkDate(menu);

        Menu created = menuRepository.save(menu);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody Menu menu, @PathVariable int id) {
        log.info("update {} with id={}", menu, id);
        assureIdConsistent(menu, id);
        menuRepository.save(menu);

    }

    @GetMapping("/{id}")
    public Menu get(@PathVariable int id) {
        Menu menu = menuRepository.get(id);
        log.info("get {}", menu);
        return menu;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete {}", id);
        menuRepository.delete(id);
    }

    @GetMapping
    public List<MenuTo> getAll() {
        log.info("getAll");
        return VotingUtil.getTos(menuRepository.getAll());
    }

    @GetMapping(value = "/filter")
    public List<MenuTo> getAllByDate(@RequestParam LocalDate date) {
        log.info("getAllByDate");
        Set<Menu> temp = menuRepository.getAllByDate(date);
        log.debug(temp.toString());
        return VotingUtil.getTos(temp);
    }
}
