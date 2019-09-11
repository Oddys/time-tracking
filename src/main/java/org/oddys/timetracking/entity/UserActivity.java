package org.oddys.timetracking.entity;

public class UserActivity extends Entity {
    private Boolean assigned;
    private User user;
    private Activity activity;

    public UserActivity() {
    }

    public UserActivity(Long id, Boolean assigned, User user, Activity activity) {
        super(id);
        this.assigned = assigned;
        this.user = user;
        this.activity = activity;
    }

    public Boolean getAssigned() {
        return assigned;
    }

    public void setAssigned(Boolean assigned) {
        this.assigned = assigned;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
