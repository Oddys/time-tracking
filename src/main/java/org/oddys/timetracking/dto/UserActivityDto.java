package org.oddys.timetracking.dto;

import java.util.Objects;

public class UserActivityDto {
    private Long id;
    private Boolean assigned;
    private Long activityId;
    private String activityName;
    private Boolean statusChangeRequested;
    private Long userId;
    private String userFirstName;
    private String userLastName;

    public UserActivityDto() {}

    public UserActivityDto(Long id, Boolean assigned, Long activityId, String activityName,
            Boolean statusChangeRequested, Long userId, String userFirstName, String userLastName) {
        this.id = id;
        this.assigned = assigned;
        this.activityId = activityId;
        this.activityName = activityName;
        this.statusChangeRequested = statusChangeRequested;
        this.userId = userId;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getAssigned() {
        return assigned;
    }

    public void setAssigned(Boolean assigned) {
        this.assigned = assigned;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
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

    public Boolean getStatusChangeRequested() {
        return statusChangeRequested;
    }

    public void setStatusChangeRequested(Boolean statusChangeRequested) {
        this.statusChangeRequested = statusChangeRequested;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserActivityDto)) return false;
        UserActivityDto that = (UserActivityDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(assigned, that.assigned) &&
                Objects.equals(activityId, that.activityId) &&
                Objects.equals(activityName, that.activityName) &&
                Objects.equals(statusChangeRequested,
                        that.statusChangeRequested) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(userFirstName, that.userFirstName) &&
                Objects.equals(userLastName, that.userLastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,
                assigned,
                activityId,
                activityName,
                statusChangeRequested,
                userId,
                userFirstName,
                userLastName);
    }
}
