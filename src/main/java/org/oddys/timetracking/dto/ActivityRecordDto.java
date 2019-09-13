package org.oddys.timetracking.dto;

import java.time.LocalDate;

public class ActivityRecordDto {
    private Long id;
    private String activityName;
    private Boolean userActivityStatusChangeRequested;
    private LocalDate activityDate;
    private Long duration;
    private Long userId;
    private String userFirstName;
    private String userLastName;
    private Long activityId;

    public ActivityRecordDto() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Boolean getUserActivityStatusChangeRequested() {
        return userActivityStatusChangeRequested;
    }

    public void setUserActivityStatusChangeRequested(Boolean userActivityStatusChangeRequested) {
        this.userActivityStatusChangeRequested = userActivityStatusChangeRequested;
    }

    public LocalDate getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(LocalDate activityDate) {
        this.activityDate = activityDate;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }
}
