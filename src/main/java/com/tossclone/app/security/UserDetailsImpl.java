package com.tossclone.app.security;

import com.tossclone.app.dto.UserJoinDTO;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails {

    private String id;
    private String password;
    private List<GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return id;
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

    private UserDetailsImpl(String id, String userPassword, List<GrantedAuthority> authorities) {
        this.id = id;
        this.password = userPassword;
        this.authorities = authorities;
    }

    public static UserDetailsImpl from(UserJoinDTO userJoinDTO) {
        return new UserDetailsImpl(
                userJoinDTO.id(),
                userJoinDTO.password(),
                List.of(new SimpleGrantedAuthority("USER"))
        );
    }
}
