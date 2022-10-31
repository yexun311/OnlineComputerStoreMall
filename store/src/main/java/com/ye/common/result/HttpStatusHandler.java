package com.ye.common.result;

/**
 * http 状态接口
 * 枚举类实现
 */
public interface HttpStatusHandler {

    /** 获取 http 状态码 */
    int getHttpCode();

    /** 获取业务状态码 */
    int getCode();

    /** 获取描述信息 */
    String getMessage();

}
