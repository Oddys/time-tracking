package org.oddys.timetracking.dto;

public class UserActivityDto {
    private Long id;
    private Boolean assigned;
    private Long activityId;
    private String activityName;
    private Boolean activityApproved;

    public UserActivityDto() {}

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

    public Boolean getActivityApproved() {
        return activityApproved;
    }

    public void setActivityApproved(Boolean activityApproved) {
        this.activityApproved = activityApproved;
    }
}
