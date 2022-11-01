package com.ye.model.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户资料出参
 */
@ApiModel(description = "用户资料出参")
@Data
public class UserInfoResp {

    /** 用户id */
    private Integer uid;
    /** 用户名 */
    @ApiModelProperty("用户名")
    private String userName;
    /** 联系电话 */
    @ApiModelProperty("联系电话")
    private String phone;
    /** 电子邮箱 */
    @ApiModelProperty("电子邮箱")
    private String email;
    /** 性别：0-女，1-男 2-保密 */
    @ApiModelProperty("性别：0-女，1-男 2-保密")
    private Integer gender;
    /** 头像 */
    @ApiModelProperty("头像")
    private String avatar;

}
