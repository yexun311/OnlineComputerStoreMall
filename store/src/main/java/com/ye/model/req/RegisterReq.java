package com.ye.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 注册请求入参
 */
@Data
@ApiModel("注册请求入参")
public class RegisterReq {

    /** 账号 */
    @ApiModelProperty("账号")
    private String account;
    /** 密码 */
    @ApiModelProperty("密码")
    private String password;
    /** 用户名 */
    @ApiModelProperty("用户名")
    private String userName;

}
