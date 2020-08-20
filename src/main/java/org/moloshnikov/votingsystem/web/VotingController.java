package org.moloshnikov.votingsystem.web;

import org.moloshnikov.votingsystem.model.DayMenu;
import org.moloshnikov.votingsystem.model.Vote;
import org.moloshnikov.votingsystem.repository.daymenu.DayMenuRepository;
import org.moloshnikov.votingsystem.repository.user.UserRepository;
import org.moloshnikov.votingsystem.repository.vote.VoteRepository;
import org.moloshnikov.votingsystem.to.DayMenuTo;
import org.moloshnikov.votingsystem.util.SecurityUtil;
import org.moloshnikov.votingsystem.util.ValidationUtil;
import org.moloshnikov.votingsystem.util.VotingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = VotingController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VotingController {
    protected final Logger log = LoggerFactory.getLogger(VotingController.class);
    static final String REST_URL = "/profile/voting";

    private final DayMenuRepository dayMenuRepository;
    private final UserRepository userRepository;
    private final VoteRepository voteRepository;

    public VotingController(DayMenuRepository dayMenuRepository, UserRepository userRepository, VoteRepository voteRepository) {
        this.dayMenuRepository = dayMenuRepository;
        this.userRepository = userRepository;
        this.voteRepository = voteRepository;
    }

    @GetMapping
    public List<DayMenuTo> getAll() {
        LocalDate localDateToday = LocalDate.now();
        log.info("getAll");
        return VotingUtil.getTos(dayMenuRepository.getAllByDate(localDateToday));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> toVote(@RequestBody DayMenu dayMenu) {
        log.info("create vote for dayMenu {}", dayMenu.getId());
        LocalDateTime now = LocalDateTime.now();
        int userId = SecurityUtil.authUserId();
        ValidationUtil.checkDeadLine(now.toLocalTime());

        DayMenu selectedDayMenu = dayMenuRepository.get(dayMenu.id()); //получаю выбранное дневное меню

        Vote checkVote = voteRepository.getByUserIdDate(userId, now.toLocalDate());
        if (checkVote == null) {
            checkVote = VotingUtil.makeVote(selectedDayMenu, userRepository.get(userId));
        } else {
            checkVote.setDayMenu(selectedDayMenu);
            checkVote.setLocalDate(now.toLocalDate());
            checkVote.setLocalTime(now.toLocalTime());
        }
        voteRepository.save(checkVote);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(checkVote.getId()).toUri();

//        return new ResponseEntity<>("Ваш голос принят.",HttpStatus.CREATED);
        return ResponseEntity.created(uriOfNewResource).body(checkVote);
    }
}

