package com.imp.fluffy_mood.controller;

import com.imp.fluffy_mood.dto.UserDto;
import com.imp.fluffy_mood.entity.Message;
import com.imp.fluffy_mood.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<Message> login(@RequestBody UserDto userDto) {
        return userService.login(userDto);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto userDto) {
        return userService.register(userDto);
    }

    @GetMapping("/register/validation")
    public ResponseEntity<?> idValidation(@RequestParam String id) {
        return userService.idValidation(id);
    }
}
