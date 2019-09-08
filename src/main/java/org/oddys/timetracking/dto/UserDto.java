package org.oddys.timetracking.dto;

import org.oddys.timetracking.entity.Role;
import org.oddys.timetracking.entity.User;

public class UserDto {
    private Long userId;
    private String login;
    private String firstName;
    private String lastName;
    private RoleEnum role;

    public UserDto(User userEntity, Role roleEntity) {
        userId = userEntity.getId();
        login = userEntity.getLogin();
        firstName = userEntity.getFirstName();
        lastName = userEntity.getLastName();
        role = RoleEnum.valueOf(roleEntity.getName().toUpperCase());
    }

    public Long getUserId() {
        return userId;
    }

    public String getLogin() {
        return login;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public RoleEnum getRole() {
        return role;
    }
}
