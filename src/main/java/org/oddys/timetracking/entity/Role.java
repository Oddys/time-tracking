package org.oddys.timetracking.entity;

public class Role extends Entity {
    private String name;

    public Role() {
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
