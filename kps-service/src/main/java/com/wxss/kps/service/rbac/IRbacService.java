package com.wxss.kps.service.rbac;

import com.wxss.kps.dto.rbac.LoginReqDto;
import com.wxss.kps.dto.rbac.LoginResDto;
import com.wxss.kps.rbac.User;

import java.util.List;

public interface IRbacService {
    /**
     * 查询所有用户
     * @return
     */
    List<User> findAll();

    /**
     * 登录
     * @param reqDto
     * @return
     */
    LoginResDto login(LoginReqDto reqDto);
}
