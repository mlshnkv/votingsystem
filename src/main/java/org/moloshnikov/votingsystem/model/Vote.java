package org.moloshnikov.votingsystem.model;

import java.time.LocalDateTime;

public class Vote extends AbstractBaseEntity{
    private final LocalDateTime localDateTime;
    private final DayMenu dayMenu;
    private final User user;

    public Vote(LocalDateTime localDateTime, DayMenu dayMenu, User user) {
        this.localDateTime = localDateTime;
        this.dayMenu = dayMenu;
        this.user = user;
    }

    public Vote(Integer id, LocalDateTime localDateTime, DayMenu dayMenu, User user) {
        super(id);
        this.localDateTime = localDateTime;
        this.dayMenu = dayMenu;
        this.user = user;
    }

    public DayMenu getDayMenu() {
        return dayMenu;
    }
}
