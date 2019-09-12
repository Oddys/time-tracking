package org.oddys.timetracking.entity;

import java.time.LocalDate;

public class ActivityRecord extends Entity {
    private LocalDate date;
    private Long duration;
    private UserActivity userActivity;

    public ActivityRecord() {
    }

    public ActivityRecord(Long id, LocalDate date, Long duration, UserActivity userActivity) {
        super(id);
        this.date = date;
        this.duration = duration;
        this.userActivity = userActivity;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public UserActivity getUserActivity() {
        return userActivity;
    }

    public void setUserActivity(UserActivity userActivity) {
        this.userActivity = userActivity;
    }
}
