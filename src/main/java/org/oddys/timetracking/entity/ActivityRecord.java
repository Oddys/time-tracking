package org.oddys.timetracking.entity;

import java.time.LocalDate;

public class ActivityRecord extends Entity {
    private LocalDate date;
    private Short duration;
    private Long userActivityId;

    public ActivityRecord() {
    }

    public ActivityRecord(Long id, LocalDate date, Short duration, Long userActivityId) {
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

    public Short getDuration() {
        return duration;
    }

    public void setDuration(Short duration) {
        this.duration = duration;
    }

    public Long getUserActivityId() {
        return userActivityId;
    }

    public void setUserActivityId(Long userActivityId) {
        this.userActivityId = userActivityId;
    }
}
