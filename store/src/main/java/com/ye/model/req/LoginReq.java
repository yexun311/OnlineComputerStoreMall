package com.ye.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 登录请求入参
 */
@ApiModel("登录请求入参")
@Data
public class LoginReq {

    /** 账号 */
    @ApiModelProperty("账号")
    private String account;
    /** 密码 */
    @ApiModelProperty("密码")
    private String password;

}
