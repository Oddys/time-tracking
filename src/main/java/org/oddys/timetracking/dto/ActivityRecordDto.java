package org.oddys.timetracking.dto;

import java.time.LocalDate;
import java.util.Objects;

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

    public ActivityRecordDto(Long id, String activityName,
            Boolean userActivityStatusChangeRequested, LocalDate activityDate,
            Long duration, Long userId, String userFirstName,
            String userLastName, Long activityId) {
        this.id = id;
        this.activityName = activityName;
        this.userActivityStatusChangeRequested = userActivityStatusChangeRequested;
        this.activityDate = activityDate;
        this.duration = duration;
        this.userId = userId;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.activityId = activityId;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ActivityRecordDto)) return false;
        ActivityRecordDto that = (ActivityRecordDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(activityName, that.activityName) &&
                Objects.equals(userActivityStatusChangeRequested,
                        that.userActivityStatusChangeRequested) &&
                Objects.equals(activityDate, that.activityDate) &&
                Objects.equals(duration, that.duration) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(userFirstName, that.userFirstName) &&
                Objects.equals(userLastName, that.userLastName) &&
                Objects.equals(activityId, that.activityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,
                activityName,
                userActivityStatusChangeRequested,
                activityDate,
                duration,
                userId,
                userFirstName,
                userLastName,
                activityId);
    }
}
