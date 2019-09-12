package org.oddys.timetracking.util;

import org.oddys.timetracking.entity.Activity;
import org.oddys.timetracking.entity.ActivityRecord;
import org.oddys.timetracking.entity.Role;
import org.oddys.timetracking.entity.User;
import org.oddys.timetracking.entity.UserActivity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EntityMapper {
    public static final EntityMapper INSTANCE = new EntityMapper();

    private EntityMapper() {}

    public static EntityMapper getInstance() {
        return INSTANCE;
    }

    public ActivityRecord mapActivityRecord(ResultSet rs) throws SQLException {
        UserActivity userActivity = mapUserActivity(rs);
        return new ActivityRecord(
                rs.getLong("activity_record_id"),
                rs.getDate("activity_date").toLocalDate(),
                rs.getLong("duration"),
                userActivity
        );
    }

    public UserActivity mapUserActivity(ResultSet rs) throws SQLException {
        User user = mapUser(rs);
        Activity activity = mapActivity(rs);
        return new UserActivity(rs.getLong("user_activity_id"),
                rs.getBoolean("assigned"), user, activity);
    }

    public User mapUser(ResultSet rs) throws SQLException {
        Role role = mapRole(rs);
        return new User(
                rs.getLong("user_id"),
                rs.getString("login"),
                rs.getString("password").toCharArray(),
                rs.getString("first_name"),
                rs.getString("last_name"),
                role
        );
    }

    public Activity mapActivity(ResultSet rs) {return null;}

    public Role mapRole(ResultSet rs) throws SQLException {
        return new Role(rs.getLong("role_id"), rs.getString("role_name"));
    }
}
