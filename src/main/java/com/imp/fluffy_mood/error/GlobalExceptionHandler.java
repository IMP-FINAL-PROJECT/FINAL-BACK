package com.imp.fluffy_mood.error;

import com.imp.fluffy_mood.enums.StatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.TransactionException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /*
    공통 예외 처리만 미리 해둠
    만약, 로그인 시 발생하는 예외, 회원가입 시 발생하는 예외 각자 설정이 필요한 경우 수정
    */

    // 400
    @ExceptionHandler({
            MethodArgumentNotValidException.class,
            HttpMessageNotWritableException.class,
            HttpMessageNotReadableException.class,
            IllegalArgumentException.class,
            TransactionException.class
    })
    public ResponseEntity<ErrorResponse> badRequestException() {
        final ErrorResponse response = ErrorResponse.of(StatusEnum.BAD_REQUEST);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    //401
    @ExceptionHandler({
            AccessDeniedException.class
    })
    public ResponseEntity<ErrorResponse> accessDeniedException() {
        final ErrorResponse response = ErrorResponse.of(StatusEnum.UNAUTHORIZED);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    //500
    @ExceptionHandler({
            Exception.class
    })
    public ResponseEntity<ErrorResponse> handleException(final Exception ex) {
        final ErrorResponse response = ErrorResponse.of(StatusEnum.INTERNAL_SERVER_ERROR);
        log.error("error", ex);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
