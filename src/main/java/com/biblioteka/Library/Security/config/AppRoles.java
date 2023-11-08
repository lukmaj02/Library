package com.biblioteka.Library.Security.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static com.biblioteka.Library.Security.config.Permissions.*;

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
            EMPLOYEE_DELETE,
            EMPLOYEE_UPDATE

    )),
    EMPLOYEE(Set.of(
            EMPLOYEE_READ,
            EMPLOYEE_CREATE,
            EMPLOYEE_DELETE,
            EMPLOYEE_UPDATE
    ));

    @Getter
    private final Set<Permissions> permissions;

    public Set<SimpleGrantedAuthority> getAuthorities(){
        var authorities =  getPermissions()
                .stream()
                .map(permissions -> new SimpleGrantedAuthority(permissions.getPermission()))
                .collect(Collectors.toSet());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
