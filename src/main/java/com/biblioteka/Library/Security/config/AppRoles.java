package com.biblioteka.Library.Security.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static com.biblioteka.Library.Security.config.Permission.*;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public enum AppRoles {
    USER(Set.of(
            USER_READ,
            USER_UPDATE
    )),

    ADMIN(Set.of(
            ADMIN_READ,
            ADMIN_CREATE,
            ADMIN_DELETE,
            ADMIN_UPDATE,
            EMPLOYEE_READ,
            EMPLOYEE_CREATE,
            EMPLOYEE_DELETE
    )),
    EMPLOYEE(Set.of(
            EMPLOYEE_READ,
            EMPLOYEE_CREATE,
            EMPLOYEE_DELETE
    ));

    @Getter
    private final Set<Permission> permissions;

    public Set<SimpleGrantedAuthority> getAuthorities(){
        var authorities =  getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .collect(Collectors.toSet());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
