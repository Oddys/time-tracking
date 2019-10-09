package org.oddys.timetracking.dto;

import org.oddys.timetracking.entity.UserActivity;

import java.util.Objects;

public class ActivityRecordsPage extends PageDto<ActivityRecordDto> {
    private Long userActivityId;
    private Boolean assigned;
    private String activityName;
    private Long userId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUserActivityData(UserActivity activity) {
        userActivityId = activity.getId();
        activityName = activity.getActivity().getName();
        assigned = activity.getAssigned();
        userId = activity.getUser().getId();
        userFirstName = activity.getUser().getFirstName();
        userLastName = activity.getUser().getLastName();
    }

    public boolean isEmpty() {
        return userActivityId == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ActivityRecordsPage)) return false;
        if (!super.equals(o)) return false;
        ActivityRecordsPage that = (ActivityRecordsPage) o;
        return Objects.equals(userActivityId, that.userActivityId) &&
                Objects.equals(assigned, that.assigned) &&
                Objects.equals(activityName, that.activityName) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(userFirstName, that.userFirstName) &&
                Objects.equals(userLastName, that.userLastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(),
                userActivityId,
                assigned,
                activityName,
                userId,
                userFirstName,
                userLastName);
    }
}
