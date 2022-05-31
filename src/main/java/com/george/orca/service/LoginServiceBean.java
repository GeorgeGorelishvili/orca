package com.george.orca.service;

import com.george.orca.WebSecurityConfiguration;
import com.george.orca.domain.UserEntity;
import com.george.orca.repository.LoginRepository;
import com.george.orca.utils.TemplateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class LoginServiceBean implements UserDetailsService {

    private final LoginRepository loginRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserEntity> optionalUserEntity = loginRepository.findByUsername(username);

        optionalUserEntity.orElseThrow(() -> new UsernameNotFoundException("user not found : "  +  username));

        UserEntity userEntity = new TemplateUtil<UserEntity>().get(optionalUserEntity);

        UserDetails user = User.withUsername(userEntity.getUsername())
                .password(userEntity.getPassword())
                .authorities("USER").build();

        return user;
    }
}
