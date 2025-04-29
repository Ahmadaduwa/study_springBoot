package com.study.study.models.Auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Collectors;

public class UserPrincipal implements UserDetails, Serializable {

    private Users users;

    public UserPrincipal() {
    }

    public UserPrincipal(Users users) {
        this.users = users;
    }

    //@Override
    //public Collection<? extends GrantedAuthority> getAuthorities() {
    //    return Collections.singleton(new SimpleGrantedAuthority("USER"));
    //}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return users.getRoles().stream()
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return users.getPassword();
    }

    @Override
    public String getUsername() {
        return users.getUsername();
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
