package org.oddys.timetracking.entity;

import java.time.LocalDate;

public class ActivityRecord extends Entity {
    private LocalDate date;
    private Long duration;
    private Long userActivityId;

    public ActivityRecord() {
    }

    public ActivityRecord(Long id, LocalDate date, Long duration, Long userActivityId) {
        super(id);
        this.date = date;
        this.duration = duration;
        this.userActivityId = userActivityId;
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

    public Long getUserActivityId() {
        return userActivityId;
    }

    public void setUserActivityId(Long userActivityId) {
        this.userActivityId = userActivityId;
    }
}
