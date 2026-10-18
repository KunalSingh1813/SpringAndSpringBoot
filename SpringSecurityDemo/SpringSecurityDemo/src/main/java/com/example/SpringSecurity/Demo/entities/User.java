package com.example.SpringSecurity.Demo.entities;

import com.example.SpringSecurity.Demo.entities.enums.Role;
import com.example.SpringSecurity.Demo.utils.PermissionMapping;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Permission;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;
    private String password;
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        roles.forEach(
                role->{
                   Set<SimpleGrantedAuthority> permissions = PermissionMapping.getAuthoritiesForRole(role);
                   authorities.addAll(permissions);
                   authorities.add(new SimpleGrantedAuthority("ROLE_"+role.name()));
                }
        );
//        Set<SimpleGrantedAuthority> authorities = roles.stream()
//                .map(role-> new SimpleGrantedAuthority("ROLE_"+role.name()))
//                .collect(Collectors.toSet());
//        permission.stream().forEach(
//                permission -> authorities.add(new SimpleGrantedAuthority(permission.getName()))
//        );
                return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }
}
