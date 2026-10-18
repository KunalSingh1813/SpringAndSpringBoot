package com.example.SpringSecurity.Demo.utils;

import com.example.SpringSecurity.Demo.entities.enums.Permissions;
import com.example.SpringSecurity.Demo.entities.enums.Role;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.SpringSecurity.Demo.entities.enums.Permissions.*;
import static com.example.SpringSecurity.Demo.entities.enums.Role.*;

public class PermissionMapping {
    private static final Map<Role, Set<Permissions>> map = Map.of(
      USER, Set.of(USER_VIEW, POSt_VIEW),
      CREATOR ,   Set.of(POST_CREATE, USER_UPDATE,POST_UPDATE),
      ADMIN, Set.of(USER_DELETE,USER_UPDATE,POST_UPDATE,POST_DELETE,USER_CREATE)
    );

    public static Set<SimpleGrantedAuthority> getAuthoritiesForRole(Role role){
        return map.get(role).stream()
                .map(permissions -> new SimpleGrantedAuthority(permissions.name()))
                .collect(Collectors.toSet());
    }
}
