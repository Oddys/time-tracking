package org.oddys.timetracking.dto;

import java.util.List;
import java.util.Objects;

public class UserActivitiesDto {
    private Long userId;
    private String firstName;
    private String lastName;
    private List<UserActivityDto> userActivities;

    public UserActivitiesDto() {}

    public UserActivitiesDto(Long userId, String firstName, String lastName,
            List<UserActivityDto> userActivities) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userActivities = userActivities;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserActivitiesDto)) return false;
        UserActivitiesDto that = (UserActivitiesDto) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(userActivities, that.userActivities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, firstName, lastName, userActivities);
    }
}
