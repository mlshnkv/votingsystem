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
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping(value = DayMenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DayMenuController {
    private final LocalDate localDateToday = LocalDate.now();
    protected final Logger log = LoggerFactory.getLogger(getClass());
    static final String REST_URL = "/profile/voting";

    private final DayMenuRepository dayMenuRepository;
    private final UserRepository userRepository;
    private final VoteRepository voteRepository;

    public DayMenuController(DayMenuRepository dayMenuRepository, UserRepository userRepository, VoteRepository voteRepository) {
        this.dayMenuRepository = dayMenuRepository;
        this.userRepository = userRepository;
        this.voteRepository = voteRepository;
    }

    @GetMapping
    public List<DayMenuTo> getAllOfToday() {
        log.info("getAll");
        return VotingUtil.getTos(dayMenuRepository.getAllByDate(localDateToday));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> createVote(@RequestBody DayMenu dayMenu) {
        int userId = SecurityUtil.authUserId();
        LocalTime votingTime = LocalTime.now();
        ValidationUtil.checkDeadLine(votingTime);
        System.out.println(dayMenu.getId());
        DayMenu dayMenu1 = dayMenuRepository.get(dayMenu.id());
        Vote created = VotingUtil.makeVote(dayMenu1, userRepository.get(userId));
        voteRepository.save(created);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}

