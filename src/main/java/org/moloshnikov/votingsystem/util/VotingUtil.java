package org.moloshnikov.votingsystem.util;

import org.moloshnikov.votingsystem.model.Menu;
import org.moloshnikov.votingsystem.model.User;
import org.moloshnikov.votingsystem.model.Vote;
import org.moloshnikov.votingsystem.to.MenuTo;
import org.moloshnikov.votingsystem.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class VotingUtil {

    public static List<MenuTo> getTos(Collection<Menu> menus) {

        return menus.stream()
                .map(VotingUtil::createTo)
                .collect(Collectors.toList());
    }

    public static MenuTo createTo(Menu menu) {
        return new MenuTo(menu.getId(), menu.getDate(), menu.getRestaurant(), menu.getDishes(), menu.getVotes().size());
    }

    public static Vote makeVote(Menu menu, User user) {
        return new Vote(LocalDate.now(), LocalTime.now(), menu, user);
    }

    public static void checkMenuAvailability(Set<Menu> menuList, Menu menu) {
        if (!menuList.contains(menu)) {
            throw new NotFoundException("Sorry, this option was not found or does not exist");
        }
    }
}
