package org.oddys.timetracking.util;

import org.oddys.timetracking.entity.Activity;
import org.oddys.timetracking.entity.ActivityRecord;
import org.oddys.timetracking.entity.Role;
import org.oddys.timetracking.entity.User;
import org.oddys.timetracking.entity.UserActivity;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class EntityMapper {
    public static final EntityMapper INSTANCE = new EntityMapper();

    private EntityMapper() {}

    public static EntityMapper getInstance() {
        return INSTANCE;
    }

    public ActivityRecord mapActivityRecord(ResultSet rs) throws SQLException {
        UserActivity userActivity = mapUserActivity(rs);
        return new ActivityRecord(rs.getLong("activity_record_id"),
//                                  rs.getDate("activity_date").toLocalDate(),
                                  rs.getObject("activity_date", LocalDate.class),
                                  rs.getLong("duration"),
                                  userActivity);
    }

    public UserActivity mapUserActivity(ResultSet rs) throws SQLException {
        User user = mapUser(rs);
        Activity activity = mapActivity(rs);
        return new UserActivity(rs.getLong("user_activity_id"),
                                rs.getBoolean("assigned"),
                                rs.getBoolean("status_change_requested"),
                                user,
                                activity);
    }

    public User mapUser(ResultSet rs) throws SQLException {
        Role role = mapRole(rs);
        return new User(rs.getLong("user_id"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        role);
    }

    public User mapUser(HttpServletRequest req) {
        Role role = new Role(Long.valueOf(req.getParameter("roleId")), null);
        return new User(null,
                        req.getParameter("login"),
                        PasswordManager.getInstance().hashPassword(
                                req.getParameter("password")),
                        req.getParameter("firstName"),
                        req.getParameter("lastName"),
                        role);
    }

    public Activity mapActivity(ResultSet rs) throws SQLException {
        return new Activity(rs.getLong("activity_id"),
                            rs.getString("activity_name"));
    }

    public Role mapRole(ResultSet rs) throws SQLException {
        return new Role(rs.getLong("role_id"), rs.getString("role_name"));
    }
}
