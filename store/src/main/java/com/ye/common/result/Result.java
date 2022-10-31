package com.ye.common.result;

/**
 * 封装 ResultSet 结果集
 */
public class Result {

    /** 调用 resultSet 构造方法返回 resultSet 对象 */
    public static ResultSet<Void> resultSet(int code, String msg){
        return new ResultSet<>(code, msg);
    }

    public static <T> ResultSet<T> resultSet(int code, String msg, T data){
        return new ResultSet<>(code, msg, data);
    }

    public static ResultSet<Void> resultSet(HttpStatusHandler handler){
        return new ResultSet<>(handler.getCode(), handler.getMessage());
    }

    public static <T> ResultSet<T> resultSet(HttpStatusHandler handler, T data){
        return new ResultSet<>(handler.getCode(), handler.getMessage(), data);
    }

    /**
     * 响应成功
     * 参数：
     * 1.无参数
     * 2.描述信息
     * 3.返回数据
     * 4.描述信息 + 返回数据
     * 5.状态枚举
     * 6.状态枚举 + 描述信息
     * 7.状态枚举 + 返回数据
     * 8.状态枚举 + 描述信息 + 返回数据
     * ps:
     *   状态枚举默认为 HttpStatusEnum.SUCCESS
     *   msg 为空则返回枚举的状态信息
     */
    public static ResultSet<Void> success(){
        return resultSet(HttpStatusEnum.SUCCESS);
    }

    public static ResultSet<Void> success(String msg){
        if (msg != null && !"".equals(msg))
            return resultSet(HttpStatusEnum.SUCCESS.getCode(), msg);
        return success();
    }

    public static <T> ResultSet<T> success(T data){
        return resultSet(HttpStatusEnum.SUCCESS, data);
    }

    public static <T> ResultSet<T> success(String msg, T data){
        if (msg != null && !"".equals(msg))
            return resultSet(HttpStatusEnum.SUCCESS.getCode(), msg, data);
        return success(HttpStatusEnum.SUCCESS, data);
    }

    public static ResultSet<Void> success(HttpStatusHandler handler){
        return resultSet(handler);
    }

    public static ResultSet<Void> success(HttpStatusHandler handler, String msg){
        return resultSet(handler.getCode(), msg != null && !"".equals(msg) ? msg : handler.getMessage());
    }

    public static <T> ResultSet<T> success(HttpStatusHandler handler, T data){
        return resultSet(handler, data);
    }

    public static <T> ResultSet<T> success(HttpStatusHandler handler, String msg, T data){
        return resultSet(handler.getCode(), msg != null && !"".equals(msg) ? msg : handler.getMessage(), data);
    }


    /**
     * 响应失败
     * 参数：
     * 1.无参数
     * 2.描述信息
     * 3.返回数据
     * 4.描述信息 + 返回数据
     * 5.状态枚举
     * 6.状态枚举 + 描述信息
     * 7.状态枚举 + 返回数据
     * 8.状态枚举 + 描述信息 + 返回数据
     * ps:
     *   状态枚举默认为 HttpStatusEnum.FAIL
     *   msg 为空则返回枚举的状态信息
     */
    public static ResultSet<Void> fail(){
        return resultSet(HttpStatusEnum.FAIL);
    }

    public static ResultSet<Void> fail(String msg){
        if (msg != null && !"".equals(msg))
            return resultSet(HttpStatusEnum.FAIL.getCode(), msg);
        return fail();
    }

    public static <T> ResultSet<T> fail(T data){
        return resultSet(HttpStatusEnum.FAIL, data);
    }

    public static <T> ResultSet<T> fail(String msg, T data){
        if (msg != null && !"".equals(msg))
            return resultSet(HttpStatusEnum.FAIL.getCode(), msg, data);
        return fail(HttpStatusEnum.FAIL, data);
    }

    public static ResultSet<Void> fail(HttpStatusHandler handler){
        return resultSet(handler);
    }

    /** msg 为空则返回枚举的状态信息 */
    public static ResultSet<Void> fail(HttpStatusHandler handler, String msg){
        return resultSet(handler.getCode(), msg != null && !"".equals(msg) ? msg : handler.getMessage());
    }

    public static <T> ResultSet<T> fail(HttpStatusHandler handler, T data){
        return resultSet(handler, data);
    }

    /** msg 为空则返回枚举的状态信息 */
    public static <T> ResultSet<T> fail(HttpStatusHandler handler, String msg, T data){
        return resultSet(handler.getCode(), msg != null && !"".equals(msg) ? msg : handler.getMessage(), data);
    }

}
