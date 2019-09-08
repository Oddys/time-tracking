package org.oddys.timetracking.dto;

import org.oddys.timetracking.entity.Role;
import org.oddys.timetracking.entity.User;

public class UserDto {
    private Long userId;
    private String login;
    private String firstName;
    private String lastName;
//    private RoleEnum role;
    private Long roleId;
    private String roleName;

//    public UserDto(User userEntity, Role roleEntity) {
//        userId = userEntity.getId();
//        login = userEntity.getLogin();
//        firstName = userEntity.getFirstName();
//        lastName = userEntity.getLastName();
//        role = RoleEnum.valueOf(roleEntity.getName().toUpperCase());
//    }

    public UserDto(User user) {
        userId = user.getId();
        login = user.getLogin();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        roleId = user.getRole().getId();
        roleName = user.getRole().getName();
    }

    public UserDto() {
    }

    //    public Long getUserId() {
//        return userId;
//    }
//
//    public String getLogin() {
//        return login;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }

//    public RoleEnum getRole() {
//        return role;
//    }


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
