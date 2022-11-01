package com.ye.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 登录信息
 */
@Data
@ApiModel(description = "登录信息")
public class LoginDto {

    /** 用户id */
    @ApiModelProperty("用户id")
    private Integer uid;
    /** 用户名称 */
    @ApiModelProperty("用户名称")
    private String userName;
    /** 头像 */
    @ApiModelProperty("头像")
    private String avater;

}
