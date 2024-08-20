package com.tossclone.app.security;

import com.tossclone.app.dto.UserJoinDTO;
import com.tossclone.app.service.UserService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserJoinDTO> optionalUserDTO = userService.findUserById(username);
        if (optionalUserDTO.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }

        return UserDetailsImpl.from(optionalUserDTO.get());
    }
}
