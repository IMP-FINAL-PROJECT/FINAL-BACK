package com.imp.fluffy_mood.controller;

import com.imp.fluffy_mood.entity.Message;
import com.imp.fluffy_mood.service.BatchIdService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@Slf4j
@RestController
@RequestMapping("api/batch")
public class BatchIdController {

    private final BatchIdService batchIdService;

    public BatchIdController(BatchIdService batchIdService) { this.batchIdService = batchIdService; }

    @GetMapping("")
    public ResponseEntity<Message> getBatchId(HttpServletRequest request, @RequestParam String id) {
        log.debug("Accessed IP : {}", request.getRemoteAddr());
        log.debug("id : {}", id);

        ResponseEntity<Message> response = batchIdService.getBatchId(id);

        log.debug("Data : {}", response.getBody());

        return response;
    }
}
