package org.oddys.timetracking.entity;

public class Activity extends Entity {
    private String name;
    private Boolean approved;

    public Activity() {
    }

    public Activity(Long id, String name, Boolean approved) {
        super(id);
        this.name = name;
        this.approved = approved;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }
}
