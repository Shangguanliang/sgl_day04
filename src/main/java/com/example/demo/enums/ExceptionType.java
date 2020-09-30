package com.example.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public enum ExceptionType {
    /**
     * 错误类型
     */
    BOOK_SAVE_ERROR(500,"新增书籍失败"),
    BOOK_NOT_FOUND(404,"书籍信息不存在"),
    BOOK_UPDATE_ERROR(500,"修改书籍失败"),
    CATEGORY_SAVE_ERROR(500,"新增分类失败"),
    CATEGORY_NOT_FOUND(404,"分类不存在"),
    ;
    private Integer status;//状态码
    private String msg;//报错信息

}
