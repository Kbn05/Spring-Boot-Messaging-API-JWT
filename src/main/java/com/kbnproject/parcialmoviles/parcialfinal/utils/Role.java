package com.kbnproject.parcialmoviles.parcialfinal.utils;

import java.util.Arrays;
import java.util.List;

public enum Role {
    ADMIN(Arrays.asList(Permission.DELETE_USER_FROM_COMMUNITY, Permission.DELETE_COMMUNITY, Permission.ADD_USER_TO_COMMUNITY, Permission.MODIFY_COMMUNITY, Permission.BASIC_PERMISSION)),
    USER(Arrays.asList(Permission.DELETE_FROM_COMMUNITY, Permission.ADD_TO_COMMUNITY, Permission.BASIC_PERMISSION));

    private List<Permission> permissions;

    Role(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
