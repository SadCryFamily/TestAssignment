package com.project.backend.advice;

import com.project.backend.exception.UserRegisterNotAllowedByBirthException;
import com.project.backend.util.ErrorsMapperUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class UserAdvice {

    @ExceptionHandler({UserRegisterNotAllowedByBirthException.class})
    public ResponseEntity<Map<String, List<String>>> handleClientExceptions(RuntimeException e) {
        List<String> errorsList = Collections.singletonList(e.getMessage());

        return new ResponseEntity<>(ErrorsMapperUtil.getErrorsMap(errorsList),
                new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

}
