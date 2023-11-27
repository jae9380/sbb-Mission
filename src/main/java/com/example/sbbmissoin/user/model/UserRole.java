package com.example.sbbmissoin.user.model;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("ROLE_ADMIN"), USER("ROLE_USER");

    UserRole(String vaulue){
        this.value=vaulue;
    }

    private String value;
}
