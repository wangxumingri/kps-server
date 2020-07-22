package com.wxss.kps.admin.controller;

import com.wxss.kps.dao.UserDao;
import com.wxss.kps.dto.rbac.LoginReqDto;
import com.wxss.kps.dto.rbac.LoginResDto;
import com.wxss.kps.rbac.User;
import com.wxss.kps.service.rbac.IRbacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("")
public class LoginController {

    @Autowired
    private IRbacService rbacService;

    @PostMapping("login")
    @ResponseBody
    public LoginResDto login(@RequestBody LoginReqDto loginReqDto){
        return rbacService.login(loginReqDto);
    }

}
