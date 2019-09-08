package org.oddys.timetracking.entity;

import java.util.Arrays;

public class User extends Entity {
    private String login;
    private char[] password;
    private String firstName;
    private String lastName;
    private Long roleId;

    public User() {
    }

    public User(Long id, String login, char[] password, String firstName, String lastName, Long roleId) {
        super(id);
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roleId = roleId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public char[] getPassword() {
        return Arrays.copyOf(password, password.length);
    }

    public void setPassword(char[] password) {
        this.password = Arrays.copyOf(password, password.length);
    }

    public void setPassword(String password) {
        this.password = password.toCharArray();
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
}
