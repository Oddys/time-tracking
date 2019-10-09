package org.oddys.timetracking.entity;

public class Activity extends Entity {
    private String name;

    public Activity() {
    }

    public Activity(Long id, String name) {
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
