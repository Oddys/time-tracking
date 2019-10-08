package org.oddys.timetracking.dto;

import org.oddys.timetracking.entity.UserActivity;

public class ActivityRecordsPage extends PageDto<ActivityRecordDto> {
    private Long userActivityId;
    private Boolean assigned;
    private String activityName;
    private String userFirstName;
    private String userLastName;

    public ActivityRecordsPage() {}

    public Long getUserActivityId() {
        return userActivityId;
    }

    public void setUserActivityId(Long userActivityId) {
        this.userActivityId = userActivityId;
    }

    public Boolean getAssigned() {
        return assigned;
    }

    public void setAssigned(Boolean assigned) {
        this.assigned = assigned;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
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

    public void setUserActivityData(UserActivity activity) {
        userActivityId = activity.getId();
        activityName = activity.getActivity().getName();
        assigned = activity.getAssigned();
        userFirstName = activity.getUser().getFirstName();
        userLastName = activity.getUser().getLastName();
    }

    public boolean isEmpty() {
        return userActivityId == null;
    }
}
