package com.store.api.service.imple;

import com.store.api.persistence.model.entities.UserEntity;
import com.store.api.persistence.repository.IUserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IUserEntityRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findUserByName(username).orElseThrow(() -> new UsernameNotFoundException("User not found"+ username));

        List <SimpleGrantedAuthority> authorities = new ArrayList<>();

        userEntity.getRoles()
                .forEach(role -> authorities
                        .add(new SimpleGrantedAuthority("ROLE_"
                                .concat(role.getRole().name()))));
        userEntity.getRoles().stream()
                .flatMap(roles->roles.getPermissionList().stream())
                        .forEach(permission -> authorities
                                .add(new SimpleGrantedAuthority(permission.getName())));

        return new User(userEntity.getUsername(), userEntity.getPassword(), userEntity.isEnabled(), userEntity.isAccountNonExpired(),
                userEntity.isAccountNonLocked(), userEntity.isCredentialsNonExpired(), authorities);
    }
}
