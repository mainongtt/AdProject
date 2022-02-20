package com.jd.service.impl;

import com.jd.constant.CommonStatus;
import com.jd.constant.Constants;
import com.jd.dao.AdPlanDao;
import com.jd.dao.AdUserDao;
import com.jd.entity.AdPlan;
import com.jd.entity.AdUser;
import com.jd.exception.AdException;
import com.jd.service.IAdPlanService;
import com.jd.utils.CommonUtils;
import com.jd.vo.AdPlanGetRequest;
import com.jd.vo.AdPlanRequest;
import com.jd.vo.AdPlanResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class IAdPlanServiceImpl implements IAdPlanService {

    @Autowired(required = false)
    private AdUserDao adUserDao;
    @Autowired(required = false)
    private AdPlanDao adPlanDao;

    @Override
    @Transactional
    public AdPlanResponse createAdPlan(AdPlanRequest request) throws AdException {
        if(!request.createValidate()){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        List<AdUser> userList = adUserDao.findAllUser(new AdUser(request.getId()));
        if(userList.isEmpty()){
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }
        List<AdPlan> planList = adPlanDao.findAllPlan(new AdPlan(request.getUserId(), request.getPlanName()));
        if(!planList.isEmpty()){
            throw new AdException(Constants.ErrorMsg.SAME_NAME_ERROR);
        }
        AdPlan adPlan = new AdPlan(request.getUserId(), request.getPlanName(), CommonUtils.parseStringDate(request.getStartDate()), CommonUtils.parseStringDate(request.getEndDate()));
        adPlanDao.save(adPlan);
        return new AdPlanResponse(adPlan.getId(),adPlan.getPlanName());
    }

    @Override
    public List<AdPlan> getAdPlanByIds(AdPlanGetRequest request) throws AdException {
        List<AdPlan> res = new ArrayList<>();
        List<Long> ids = request.getIds();
        for(Long id : ids){
            List<AdPlan> allPlan = adPlanDao.findAllPlan(new AdPlan(id));
            res.add(allPlan.get(0));
        }
        return res;
    }

    @Override
    @Transactional
    public AdPlanResponse updateAdPlan(AdPlanRequest request) throws AdException {
        if (!request.updateValidate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        List<AdPlan> allPlan = adPlanDao.findAllPlan(new AdPlan(request.getId()));
        AdPlan plan = allPlan.get(0);
        if (plan == null) {
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }

        if (request.getPlanName() != null) {
            plan.setPlanName(request.getPlanName());
        }
        if (request.getStartDate() != null) {
            plan.setStartDate(
                    CommonUtils.parseStringDate(request.getStartDate())
            );
        }
        if (request.getEndDate() != null) {
            plan.setEndDate(
                    CommonUtils.parseStringDate(request.getEndDate())
            );
        }

        plan.setUpdateTime(new Date());
        adPlanDao.updatePlan(plan);
        return new AdPlanResponse(plan.getId(), plan.getPlanName());
    }

    @Override
    @Transactional
    public void deleteAdPlan(AdPlanRequest request) throws AdException {
        if (!request.deleteValidate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }


        List<AdPlan> allPlan = adPlanDao.findAllPlan(new AdPlan(request.getId()));
        AdPlan plan = allPlan.get(0);
        if (plan == null) {
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }

        plan.setPlanStatus(CommonStatus.INVALID.getStatus());
        plan.setUpdateTime(new Date());
        adPlanDao.updatePlan(plan);
    }
}
