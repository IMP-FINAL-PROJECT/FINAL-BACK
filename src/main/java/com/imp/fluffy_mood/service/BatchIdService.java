package com.imp.fluffy_mood.service;

import com.imp.fluffy_mood.entity.BatchId;
import com.imp.fluffy_mood.entity.Message;
import com.imp.fluffy_mood.enums.StatusEnum;
import com.imp.fluffy_mood.repository.BatchIdRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BatchIdService {

    private final BatchIdRepository batchIdRepository;

    public ResponseEntity<Message> getBatchId(String id) {

        Message message = new Message();

        BatchId batchId = batchIdRepository.findById(id);

        if(batchId != null) {
            message.setStatus(StatusEnum.OK.getStatusCode());
            message.setResult(true);
            message.setMessage("Success");
            message.setData(batchId);
        } else {
            message.setStatus(StatusEnum.BAD_REQUEST.getStatusCode());
            message.setResult(false);
            message.setMessage("No Data");
            message.setData(null);
        }

        return ResponseEntity.ok(message);

    }

    public ResponseEntity<Message> getBatchNumber(int number) {

        Message message = new Message();

        BatchId batchNumber = batchIdRepository.findByNumber(number);

        if(batchNumber != null) {
            message.setStatus(StatusEnum.OK.getStatusCode());
            message.setResult(true);
            message.setMessage("Success");
            message.setData(batchNumber);
        } else {
            message.setStatus(StatusEnum.BAD_REQUEST.getStatusCode());
            message.setResult(false);
            message.setMessage("No Data");
            message.setData(null);
        }

        return ResponseEntity.ok(message);
    }
}
