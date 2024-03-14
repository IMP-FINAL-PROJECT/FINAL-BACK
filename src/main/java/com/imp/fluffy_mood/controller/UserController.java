package com.imp.fluffy_mood.controller;

import com.imp.fluffy_mood.dto.UserDto;
import com.imp.fluffy_mood.entity.Message;
import com.imp.fluffy_mood.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<Message> login(HttpServletRequest request, @RequestBody UserDto userDto) {
        log.debug("Accessed IP : {}", request.getRemoteAddr());
        log.debug("id : {}", userDto.getId());
        return userService.login(userDto);
    }

    @PostMapping("/register")
    public ResponseEntity<Message> register(HttpServletRequest request, @RequestBody UserDto userDto) {
        System.out.println(userDto.getBirth());
        log.debug("Accessed IP : {}", request.getRemoteAddr());
        log.debug("id : {}, birth : {}, name : {}, gender : {}", userDto.getId(), userDto.getBirth(), userDto.getName(), userDto.getGender());
        return userService.register(userDto);
    }

    @GetMapping("/register/validation")
    public ResponseEntity<Message> idValidation(HttpServletRequest request, @RequestParam String id) {
        log.debug("Accessed IP : {}", request.getRemoteAddr());
        log.debug("id : {}", id);
        return userService.idValidation(id);
    }

    @PutMapping("/change/{id}")
    public ResponseEntity<Message> changeInfo(HttpServletRequest request, @PathVariable String id, @RequestBody UserDto userDto) {
        log.debug("Accessed IP : {}", request.getRemoteAddr());
        log.debug("id : {}, name : {}, birth : {}, gender : {}", id, userDto.getName(), userDto.getBirth(), userDto.getGender());
        return userService.changeInfo(id, userDto);
    }
}
