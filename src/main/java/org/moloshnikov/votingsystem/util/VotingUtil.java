package org.moloshnikov.votingsystem.util;

import org.moloshnikov.votingsystem.model.DayMenu;
import org.moloshnikov.votingsystem.model.User;
import org.moloshnikov.votingsystem.model.Vote;
import org.moloshnikov.votingsystem.to.DayMenuTo;
import org.moloshnikov.votingsystem.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class VotingUtil {

    public static List<DayMenuTo> getTos(Collection<DayMenu> dayMenus) {

        return dayMenus.stream()
                .map(VotingUtil::createTo)
                .collect(Collectors.toList());
    }

    public static DayMenuTo createTo(DayMenu dayMenu) {
        return new DayMenuTo(dayMenu.getId(), dayMenu.getDate(), dayMenu.getRestaurant(), dayMenu.getDishes(), dayMenu.getVotes().size());
    }

    public static Vote makeVote(DayMenu dayMenu, User user) {
        return new Vote(LocalDate.now(), LocalTime.now(), dayMenu, user);
    }

    public static void checkMenuAvailability(Set<DayMenu> menuList, DayMenu dayMenu) {
        if (!menuList.contains(dayMenu)) {
            throw new NotFoundException("Sorry, this option was not found or does not exist");
        }
    }
}
