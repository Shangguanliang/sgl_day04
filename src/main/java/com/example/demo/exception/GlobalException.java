package com.example.demo.exception;

import com.example.demo.enums.ExceptionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 自定义异常 全局异常
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GlobalException extends RuntimeException{
    private ExceptionType exceptionType;

}
