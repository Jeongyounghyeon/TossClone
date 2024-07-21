package com.tossclone.app.security;

import com.tossclone.app.dto.UserDTO;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails {

    private String userId;
    private String userPassword;
    private List<GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return userPassword;
    }

    @Override
    public String getUsername() {
        return userId;
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

    private UserDetailsImpl(String userId, String userPassword, List<GrantedAuthority> authorities) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.authorities = authorities;
    }

    public static UserDetailsImpl from(UserDTO userDTO) {
        return new UserDetailsImpl(
                userDTO.userId(),
                userDTO.userPassword(),
                List.of(new SimpleGrantedAuthority("USER"))
        );
    }
}
