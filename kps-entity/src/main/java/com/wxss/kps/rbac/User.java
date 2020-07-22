package com.wxss.kps.rbac;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String uid;
    private String username;
    private String password;
    private String realName;
    private String nickName;
    private String phone;
    private String email;
}
