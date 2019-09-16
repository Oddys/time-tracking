package org.oddys.timetracking.dto;

public class ActivityDto {
    private Long id;
    private String name;
    private Boolean approved;

    public ActivityDto() {
    }

    public ActivityDto(Long id, String name, Boolean approved) {
        this.id = id;
        this.name = name;
        this.approved = approved;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }
}
