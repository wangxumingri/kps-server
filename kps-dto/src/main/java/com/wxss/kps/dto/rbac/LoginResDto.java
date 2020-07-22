package com.wxss.kps.dto.rbac;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginResDto implements Serializable {
    private Boolean flag;
    private String msg;
}
