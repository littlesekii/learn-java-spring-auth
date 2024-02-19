package com.littlesekii.auth.modules.user.entity;


import com.littlesekii.auth.modules.user.entity.enums.UserRole;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements Serializable, UserDetails {

    @Serial
    private static final long serialVersionUID = -5700262420681182120L;

    private UUID id;
    private String username;
    private String password;
    private UserRole role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> roles = new ArrayList<>();

        //add the user role
        roles.add(new SimpleGrantedAuthority("ROLE_USER"));

        //add the admin role
        if (this.role == UserRole.ADMIN)
            roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        return roles;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
