package org.moloshnikov.votingsystem.web;

import org.moloshnikov.votingsystem.model.DayMenu;
import org.moloshnikov.votingsystem.model.Vote;
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

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping(value = DayMenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DayMenuController {
    private final LocalDate localDateToday = LocalDate.now();
    protected final Logger log = LoggerFactory.getLogger(getClass());
    static final String REST_URL = "/profile/voting";

    private final VoteRepository voteRepository;

    public DayMenuController(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    @GetMapping
    public List<DayMenuTo> getAllOfToday() {
        log.info("getAll");
        return VotingUtil.getTos(voteRepository.getAllOfTodayVotes());
    }

    @PostMapping
    public ResponseEntity<Vote> createVote(@RequestParam DayMenu dayMenu) {
        int userId = SecurityUtil.authUserId();
        LocalTime votingTime = LocalTime.now();
        ValidationUtil.checkDeadLine(votingTime);

        return null;
    }
}

