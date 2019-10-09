package org.oddys.timetracking.testdata;

import org.oddys.timetracking.entity.Activity;
import org.oddys.timetracking.entity.Role;
import org.oddys.timetracking.entity.User;
import org.oddys.timetracking.entity.UserActivity;

public class EntityStubProvider {
    public static final EntityStubProvider INSTANCE = new EntityStubProvider();

    private EntityStubProvider() {}

    public static EntityStubProvider getInstance() {
        return INSTANCE;
    }

    public Role getUserRoleStub() {
        return new Role(2L, "USER");
    }

    public User getUserStub() {
        return new User(1L, "login", "password", "John", "Doe", getUserRoleStub());
    }

    public Activity getActivityStub() {
        return new Activity(1L, "Doing Stuff");
    }

    public UserActivity getUserActivityStatusChangeNotRequestedStub() {
        return new UserActivity(1L, true, false, getUserStub(), getActivityStub());
    }
}
