package org.oddys.timetracking.dto;

import java.util.List;

public class UserActivitiesDto {
    private Long userId;
    private String firstName;
    private String lastName;
    private List<UserActivityDto> userActivities;

    public UserActivitiesDto() {}

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<UserActivityDto> getUserActivities() {
        return userActivities;
    }

    public void setUserActivities(List<UserActivityDto> userActivities) {
        this.userActivities = userActivities;
    }

    public boolean isEmpty() {
        return userId == null;
    }
}
