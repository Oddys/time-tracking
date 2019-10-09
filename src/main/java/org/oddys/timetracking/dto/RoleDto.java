package org.oddys.timetracking.dto;

import java.util.Objects;

public class RoleDto {
    private Long roleId;
    private String roleName;

    public RoleDto() {}

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoleDto)) return false;
        RoleDto roleDto = (RoleDto) o;
        return Objects.equals(roleId, roleDto.roleId) &&
                Objects.equals(roleName, roleDto.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, roleName);
    }
}
