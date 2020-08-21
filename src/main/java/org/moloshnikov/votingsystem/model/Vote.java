package org.moloshnikov.votingsystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "votes", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "date"}, name = "votes_unique_user_idx")})
public class Vote extends AbstractBaseEntity {
//    @Column(name = "date_time", nullable = false)
//    @NotNull
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
//    private LocalDateTime localDateTime;

    @Column(name = "date", nullable = false)
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate localDate;

    @Column(name = "time", nullable = false)
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime localTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "day_menu_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference(value = "dayMenu-vote")
//    @NotNull
    private DayMenu dayMenu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference(value = "user-votes")
//    @NotNull
    private User user;

    public Vote() {

    }

    public Vote(@NotNull LocalDate localDate, @NotNull LocalTime localTime, DayMenu dayMenu, User user) {
        this.localDate = localDate;
        this.localTime = localTime;
        this.dayMenu = dayMenu;
        this.user = user;
    }

    public Vote(Integer id, @NotNull LocalDate localDate, @NotNull LocalTime localTime, DayMenu dayMenu, User user) {
        super(id);
        this.localDate = localDate;
        this.localTime = localTime;
        this.dayMenu = dayMenu;
        this.user = user;
    }

    public DayMenu getDayMenu() {
        return dayMenu;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }

    public void setDayMenu(DayMenu dayMenu) {
        this.dayMenu = dayMenu;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "localDate=" + localDate +
                ", localTime=" + localTime +
                ", dayMenu=" + dayMenu.id +
//                ", user=" + user +
                ", id=" + id +
                '}';
    }
}
