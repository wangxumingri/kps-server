package com.wxss.kps.dto.rbac;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class LoginReqDto implements Serializable {
    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank
    private String password;
    private String loginType;
    private String checkCode;
}
