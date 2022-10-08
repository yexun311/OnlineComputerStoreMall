package com.ye.controller;

import com.ye.common.ResultData;
import com.ye.ex.*;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class BaseController {

    /** 操作成功状态码 */
    public static final int OK = 200;

    /**
     * 1.@ExceptionHandler表示该方法用于处理捕获抛出的异常
     * 2.什么样的异常才会被这个方法处理呢?所以需要ServiceException.class,这样的话只要是抛出ServiceException异常就会被拦截到handleException方法,此时handleException方法就是请求处理方法,返回值就是需要传递给前端的数据
     * 3.被ExceptionHandler修饰后如果项目发生异常,那么异常对象就会被自动传递给此方法的参数列表上,所以形参就需要写Throwable e用来接收异常对象
     */
    @ExceptionHandler(ServiceException.class)
    public ResultData<Void> handleException(Throwable e) {
        ResultData<Void> result = new ResultData<>();
        if (e instanceof NullException) {
            result.setCode(3000);
            result.setMessage("用户名或密码为空");
        }
        if (e instanceof UsernameDuplicatedException) {
            result.setCode(4000);
            result.setMessage("用户名已经被占用");
        } else if (e instanceof InsertException) {
            result.setCode(5000);
            result.setMessage("插入数据时产生未知的异常");
        }else if (e instanceof UserNotExistException){
            result.setCode(4001);
            result.setMessage("用户不存在");
        }else if (e instanceof PasswordNotMachException){
            result.setCode(4002);
            result.setMessage("密码错误");
        }
        return result;
    }
}