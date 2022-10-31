package com.ye.common.result;

/**
 * http 状态枚举
 */
public enum HttpStatusEnum implements HttpStatusHandler {

    SUCCESS(200, 200, "操作成功"),

    FAIL(400, 400, "操作失败"),

    SYSTEM_EXCEPTION(500, 500, "系统异常"),

    ;

    /** http 状态码 */
    private int httpCode;

    /** 业务状态码 */
    private int code;

    /** 状态信息 */
    private String message;

    HttpStatusEnum(int httpCode, int code, String message) {
        this.httpCode = httpCode;
        this.code = code;
        this.message = message;
    }

    @Override
    public int getHttpCode() {
        return httpCode;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setHttpCode(int httpCode){
        this.httpCode = httpCode;
    }

    public void setCode(int code){
        this.code = code;
    }

    public void setMessage(String message){
        this.message = message;
    }

}
