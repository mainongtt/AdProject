package com.jd.service.impl;

import com.jd.constant.Constants;
import com.jd.dao.AdCreativeDao;
import com.jd.dao.AdUserDao;
import com.jd.entity.AdCreative;
import com.jd.entity.AdUser;
import com.jd.exception.AdException;
import com.jd.service.ICreativeService;
import com.jd.vo.CreativeRequest;
import com.jd.vo.CreativeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ICreativeServiceImpl implements ICreativeService {

    @Autowired(required = false)
    private AdUserDao adUserDao;
    @Autowired(required = false)
    private AdCreativeDao adCreativeDao;

    @Override
    public CreativeResponse createCreative(CreativeRequest request) throws AdException {
        Long userId = request.getUserId();
        List<AdUser> allUser =
                adUserDao.findAllUser(new AdUser(userId));
        if(allUser.isEmpty()){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        AdCreative adCreative = request.convertToEntity();
        adCreativeDao.save(adCreative);
        return new CreativeResponse(adCreative.getId(),adCreative.getName());
    }
}
