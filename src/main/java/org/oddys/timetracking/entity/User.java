package org.oddys.timetracking.entity;

public class User extends Entity {
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private Role role;

    public User() {
    }

    public User(Long id, String login, String password, String firstName,
            String lastName, Long roleId, String roleName) {
        this(id, login, password, firstName, lastName, new Role(roleId, roleName));
    }

    public User(Long id, String login, String password, String firstName, String lastName, Role role) {
        super(id);
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
