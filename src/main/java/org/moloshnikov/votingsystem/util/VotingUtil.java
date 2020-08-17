package org.moloshnikov.votingsystem.util;

import org.moloshnikov.votingsystem.model.DayMenu;
import org.moloshnikov.votingsystem.model.User;
import org.moloshnikov.votingsystem.model.Vote;
import org.moloshnikov.votingsystem.to.DayMenuTo;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class VotingUtil {

    public static List<DayMenuTo> getTos(Collection<DayMenu> dayMenus) {

        return dayMenus.stream()
                .map(dayMenu -> createTo(dayMenu, dayMenu.getVotes().size()))
                .collect(Collectors.toList());
    }

    public static DayMenuTo createTo(DayMenu dayMenu, int votes) {
        return new DayMenuTo(dayMenu.getDate(), dayMenu.getRestaurant().getName(), dayMenu.getDayMenu(), votes);
    }

    public static Vote makeVote(DayMenu dayMenu, User user) {
        return new Vote(LocalDateTime.now(), dayMenu, user);
    }
}
