package com.wxss.kps.admin.controller;

import com.wxss.http.RequestVo;
import com.wxss.http.ResponseBuilder;
import com.wxss.http.ResponseVo;
import com.wxss.kps.common.exception.ServiceException;
import com.wxss.kps.dto.rbac.LoginReqDto;
import com.wxss.kps.dto.rbac.LoginResDto;
import com.wxss.kps.service.rbac.IRbacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("")
public class LoginController {

    @Autowired
    private IRbacService rbacService;

    @PostMapping("login")
    @ResponseBody
    public ResponseVo<LoginResDto> login(@Validated @RequestBody RequestVo<LoginReqDto> requestVo) throws ServiceException {
        return ResponseBuilder.success(rbacService.login(requestVo.getBody())) ;
    }

}
