package com.ye.exception;

import com.ye.common.result.HttpStatusEnum;
import com.ye.common.result.Result;
import com.ye.common.result.ResultSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常
 */
@RestControllerAdvice
public class GlobalException {

    /*
     * 抛出异常返回的 http 状态码均为 200
     */
    private final Logger log = LoggerFactory.getLogger(GlobalException.class);

    @ExceptionHandler(FailException.class)
    public ResponseEntity<ResultSet<Void>> failExceptionHandler(FailException e){
        log.error(e.getMessage(), e);

        String msg = e.getMessage();
        if (msg == null || "".equals(msg))
            msg = HttpStatusEnum.FAIL.getMessage();

        // 返回失败状态
        return ResponseEntity
                .status(HttpStatusEnum.SUCCESS.getHttpCode())
                .body(Result.fail(HttpStatusEnum.FAIL, msg));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResultSet<Void>> exceptionHandler(Exception e){
        log.error(e.getMessage(), e);

        // 返回系统异常
        return ResponseEntity
                .status(HttpStatusEnum.SUCCESS.getHttpCode())
                .body(Result.fail(HttpStatusEnum.SYSTEM_EXCEPTION));
    }

}
