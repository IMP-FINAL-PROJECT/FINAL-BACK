package com.imp.fluffy_mood.controller;

import com.imp.fluffy_mood.entity.Message;
import com.imp.fluffy_mood.service.LogService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@CrossOrigin(origins = "*")
@Slf4j
@RestController
@RequestMapping("/api/log")
public class LogController {

    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }


    @GetMapping("")
    public ResponseEntity<Message> analysis(HttpServletRequest request, @RequestParam String id, @RequestParam LocalDate date) {

        log.debug("Accessed IP : {}", request.getRemoteAddr());
        log.debug("id : {}, Method : log", id);

        ResponseEntity<Message> response = logService.log(id,date);

        log.debug("Data : {}", response.getBody());

        return response;

    }

//    @GetMapping("/week")
//    public ResponseEntity<Message> analysisWeek(HttpServletRequest request, @RequestParam String id) {
//
//        log.debug("Accessed IP : {}", request.getRemoteAddr());
//        log.debug("id : {}, Method : analysisWeek", id);
//
//        ResponseEntity<Message> response = analysisService.analysisWeek(id);
//
//        log.debug("Data : {}", response.getBody());
//
//        return response;
//
//    }
}
