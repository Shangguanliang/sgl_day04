package com.example.demo.exception;

import com.example.demo.enums.ExceptionType;

/**
 * 异常处理返回结果封装类
 */
public class ExceptionResult {
    //状态码
    private Integer status;
    //报错信息
    private String message;
    //报错时间
    private Long timestamp;

    public ExceptionResult(ExceptionType type){
        this.status = type.getStatus();
        this.message = type.getMsg();
        this.timestamp = System.currentTimeMillis();
    }
}
