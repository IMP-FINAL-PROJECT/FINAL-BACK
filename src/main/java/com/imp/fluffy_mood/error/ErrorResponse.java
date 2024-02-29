package com.imp.fluffy_mood.error;

import com.imp.fluffy_mood.enums.StatusEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Getter
@NoArgsConstructor
public class ErrorResponse {

    private LocalDateTime timestamp = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
    private Boolean result = false;
    private int status;
    private String message;

    private ErrorResponse(final StatusEnum code) {
        this.status = code.getStatusCode();
        this.message = code.getCode();
    }

    public static ErrorResponse of(final StatusEnum code) {
        return new ErrorResponse(code);
    }
}
