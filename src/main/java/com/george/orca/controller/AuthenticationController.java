package com.george.orca.controller;

import com.george.orca.domain.UserEntity;
import com.george.orca.service.LoginServiceBean;
import com.george.orca.service.UserService;
import jdk.jfr.Description;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Objects;


@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class AuthenticationController {


    private final UserService userService;
    private final AuthenticationManager authenticationManager;

//    @Autowired
//    public void UserAuthController(@Qualifier("customAuthenticationManager") AuthenticationManager authenticationManager, UserService userService) {
//        this.authenticationManager = authenticationManager;
//        this.userService = userService;
//
//    }


    @PostMapping( "/login")
    @CrossOrigin
    public ResponseEntity<UserEntity> login(@RequestBody UserEntity user,
                                            HttpServletRequest request) {
        String username = user.getUsername();
        String password = user.getPassword();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        token.setDetails(user);


        boolean success;
        RuntimeException exToRethrow = null;
        UserEntity authenticatedUser = null;
        try {
            Authentication auth = authenticationManager.authenticate(token);
            token.setDetails(new WebAuthenticationDetails(request));
            SecurityContextHolder.getContext().setAuthentication(auth);
//            authenticatedUser = ((UserEntity) auth.getPrincipal());
            authenticatedUser = userService.getUserByUsername(username);
            success = true;
        } catch (RuntimeException e) {
            success = false;
            exToRethrow = e;
        }

    //        String ipAddress = "192.168.0.1";
    //        String userAgent = request.getHeader("User-Agent");
//        userService.registerAuthLog(userByUserName, ipAddress, userAgent, success);

        if (success) {
            return ResponseEntity.ok(authenticatedUser);
        } else {
            throw exToRethrow;
        }
    }


    @PostMapping("/logout")
    public ResponseEntity<Boolean> logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return ResponseEntity.ok(true);
    }

//    @GetMapping("/isActive")
//    public ResponseEntity<UserEntity> isActive(final Principal principal) {
//        UsernamePasswordAuthenticationToken user = (UsernamePasswordAuthenticationToken) principal;
//        if (Objects.nonNull(user) && user.isAuthenticated()) {
//            return Description.valid(((UserAdapter) user.getPrincipal()).getUser());
//        } else {
//            return Description.invalid("Not Active");
//        }
//    }
}