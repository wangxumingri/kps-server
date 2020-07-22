package com.wxss.kps.dto.rbac;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginReqDto implements Serializable {
    private String username;
    private String password;
    private String loginType;
    private String checkCode;
}
