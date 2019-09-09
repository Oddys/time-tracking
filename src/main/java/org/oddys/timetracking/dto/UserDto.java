package org.oddys.timetracking.dto;

import org.oddys.timetracking.entity.User;

import java.util.Objects;

public class UserDto {
    private Long userId;
    private String login;
    private String firstName;
    private String lastName;
    private Long roleId;
    private String roleName;

    public UserDto(User user) {
        userId = user.getId();
        login = user.getLogin();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        roleId = user.getRole().getId();
        roleName = user.getRole().getName();
    }

    public UserDto() {}

    @Override
    public int hashCode() {
        return Objects.hash(userId, login, firstName, lastName, roleId, roleName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof UserDto)) {
            return false;
        }
        UserDto o = (UserDto) obj;
        return Objects.equals(userId, o.userId) && Objects.equals(login, o.login)
                && Objects.equals(firstName, o.firstName) && Objects.equals(lastName, o.lastName)
                && Objects.equals(roleId, o.roleId) && Objects.equals(roleName, o.roleName);
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
