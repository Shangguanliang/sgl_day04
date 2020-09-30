package com.example.demo.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通用异常处理
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<ExceptionResult> handleException(GlobalException e){
        return ResponseEntity.status(e.getExceptionType().getStatus()).body(new ExceptionResult(e.getExceptionType()));
    }

    /**
     * bean校验未通过异常
     * @param be bind检验异常类
     * @return
     */
    @ResponseBody
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,Object> validExceptionHandler(BindException be){
        List<FieldError> fieldErrors = be.getBindingResult().getFieldErrors();
        return new HashMap<String,Object>(){
            private static final long serialVersionUID = 1L;
            {
                for(FieldError error:fieldErrors){
                    log.info("field = {},message = {}",error.getField(),error.getDefaultMessage());
                    put(error.getField(),error.getRejectedValue());
                }
            }
        };
    }
}
