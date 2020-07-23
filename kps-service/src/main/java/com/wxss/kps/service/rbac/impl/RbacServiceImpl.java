package com.wxss.kps.service.rbac.impl;

import com.wxss.kps.common.exception.ServiceException;
import com.wxss.kps.dao.UserDao;
import com.wxss.kps.dto.rbac.LoginReqDto;
import com.wxss.kps.dto.rbac.LoginResDto;
import com.wxss.kps.rbac.User;
import com.wxss.kps.service.rbac.IRbacService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RbacServiceImpl implements IRbacService {

    @Resource
    private UserDao userDao;

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public LoginResDto login(LoginReqDto reqDto) throws ServiceException {
        LoginResDto loginResDto = new LoginResDto();
        User userInDB = userDao.findOneByUsername(reqDto.getUsername());
        if (userInDB == null){
            throw new ServiceException("用户不存在");
        }else {
            if (userInDB.getPassword().equals(reqDto.getPassword())){
                loginResDto.setFlag(true);
            }else {
                throw new ServiceException("用户或密码错误");
            }
        }
        return loginResDto;
    }
}
