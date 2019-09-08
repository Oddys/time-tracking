package org.oddys.timetracking.entity;

public class Role extends Entity {
    private String name;

    public Role() {
    }

    public Role(Role role) {
        super(role.getId());
        this.name = role.name;
    }

    public Role(Long id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
