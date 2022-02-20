package com.jd.service.impl;

import com.jd.constant.Constants;
import com.jd.dao.AdUserDao;
import com.jd.entity.AdUser;
import com.jd.exception.AdException;
import com.jd.service.IUserService;
import com.jd.utils.CommonUtils;
import com.jd.vo.CreateUserRequest;
import com.jd.vo.CreateUserResponse;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class IUserServiceImpl implements IUserService {

    @Autowired(required = false)
    private AdUserDao adUserDao;
    @Override
    @Transactional
    public CreateUserResponse createUser(CreateUserRequest request) throws AdException {
        if(!request.validate()){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        List<AdUser> allUser = adUserDao.findAllUser(new AdUser(request.getUsername()));
        if(!allUser.isEmpty()){
            throw new AdException(Constants.ErrorMsg.SAME_NAME_ERROR);
        }
        AdUser adUser = new AdUser(request.getUsername(), CommonUtils.md5(request.getUsername()));
        int save = adUserDao.save(adUser);
        return new CreateUserResponse(adUser.getId(),adUser.getUserName(),adUser.getToken(),adUser.getCreateTime(), adUser.getUpdateTime());
    }
}
