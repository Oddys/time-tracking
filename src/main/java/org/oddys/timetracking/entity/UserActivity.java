package org.oddys.timetracking.entity;

public class UserActivity extends Entity {
    private Boolean assigned;
    private Integer userId;
    private Integer activityId;

    public UserActivity() {
    }

    public UserActivity(Integer id, Boolean assigned, Integer userId, Integer activityId) {
        super(id);
        this.assigned = assigned;
        this.userId = userId;
        this.activityId = activityId;
    }

    public Boolean isAssigned() {
        return assigned;
    }

    public void setAssigned(Boolean assigned) {
        this.assigned = assigned;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }
}
