package com.ye.common.result;

import lombok.Data;

/**
 * 统一返回对象
 */
@Data
public class ResultSet<T> {

    /** 状态码 */
    private int code;
    /** 描述信息 */
    private String message;
    /** 返回数据 */
    private T data;

    public ResultSet(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultSet(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
