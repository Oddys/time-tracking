package org.oddys.timetracking.entity;

import java.time.LocalDate;

public class ActivityRecord extends Entity {
    private LocalDate date;
    private Short duration;
    private Integer userActivityId;

    public ActivityRecord() {
    }

    public ActivityRecord(Integer id, LocalDate date, Short duration, Integer userActivityId) {
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

    public Integer getUserActivityId() {
        return userActivityId;
    }

    public void setUserActivityId(Integer userActivityId) {
        this.userActivityId = userActivityId;
    }
}
