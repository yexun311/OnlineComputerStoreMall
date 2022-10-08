package com.ye.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 统一响应类
 */
@Data
public class ResultData<T> implements Serializable {

    /** 状态码 */
    private Integer code;
    /** 描述信息 */
    private String message;
    /** 数据 */
    private T data;

    public ResultData(){}

    public ResultData(Integer code){
        this.code = code;
    }

    public ResultData(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public ResultData(Integer code, T data){
        this.code = code;
        this.data = data;
    }

    public ResultData(Integer code, String message, T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    //如果有异常,直接将异常传递给构造方法初始化对象
    public ResultData(Throwable e) {
        this.message=e.getMessage();
    }

}
