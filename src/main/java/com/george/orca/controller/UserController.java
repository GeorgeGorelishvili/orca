package com.george.orca.controller;

import com.george.orca.domain.UserEntity;
import com.george.orca.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("get")
    public ResponseEntity<UserEntity> get(@RequestParam Long userId) {
        UserEntity userEntity = userService.get(userId);
        return new ResponseEntity<>(userEntity, HttpStatus.OK);
    }
}
