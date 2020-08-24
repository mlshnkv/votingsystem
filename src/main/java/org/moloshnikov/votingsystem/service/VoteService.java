package org.moloshnikov.votingsystem.service;

import org.moloshnikov.votingsystem.model.Menu;
import org.moloshnikov.votingsystem.model.Vote;
import org.moloshnikov.votingsystem.repository.daymenu.MenuRepository;
import org.moloshnikov.votingsystem.repository.user.UserRepository;
import org.moloshnikov.votingsystem.repository.vote.VoteRepository;
import org.moloshnikov.votingsystem.to.MenuTo;
import org.moloshnikov.votingsystem.util.SecurityUtil;
import org.moloshnikov.votingsystem.util.ValidationUtil;
import org.moloshnikov.votingsystem.util.VotingUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.moloshnikov.votingsystem.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteService {
    private final VoteRepository voteRepository;
    private final MenuRepository menuRepository;
    private final UserRepository userRepository;

    public VoteService(VoteRepository voteRepository, MenuRepository menuRepository, UserRepository userRepository) {
        this.voteRepository = voteRepository;
        this.menuRepository = menuRepository;
        this.userRepository = userRepository;
    }

    public List<MenuTo> getAll() {
        LocalDate date = LocalDate.now();
        return VotingUtil.getTos(menuRepository.getAllByDate(date));
    }

    @Transactional
    public void delete(int id, int userId) {
        LocalTime now = LocalTime.now();
        ValidationUtil.checkDeadLine(now);
        checkNotFoundWithId(voteRepository.delete(id, userId), id);
    }

    @Transactional
    public Vote toVote(Menu menu) {
        LocalDateTime now = LocalDateTime.now();
        int userId = SecurityUtil.authUserId();
        ValidationUtil.checkDeadLine(now.toLocalTime());

        Menu selectedMenu = menuRepository.get(menu.id());
        VotingUtil.checkMenuAvailability(menuRepository.getAllByDate(now.toLocalDate()), selectedMenu);

        Vote checkVote = voteRepository.getByUserIdDate(userId, now.toLocalDate());
        if (checkVote == null) {
            checkVote = VotingUtil.makeVote(selectedMenu, userRepository.get(userId));
        } else {
            checkVote.setMenu(selectedMenu);
            checkVote.setLocalDate(now.toLocalDate());
            checkVote.setLocalTime(now.toLocalTime());
        }
        return voteRepository.save(checkVote);
    }
}
