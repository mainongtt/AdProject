package com.jd.service.impl;

import com.jd.constant.Constants;
import com.jd.dao.AdCreativeDao;
import com.jd.dao.AdPlanDao;
import com.jd.dao.AdUnitDao;
import com.jd.dao.unit_condition.AdUnitDistrictDao;
import com.jd.dao.unit_condition.AdUnitItDao;
import com.jd.dao.unit_condition.AdUnitKeywordDao;
import com.jd.dao.unit_condition.CreativeUnitDao;
import com.jd.entity.AdPlan;
import com.jd.entity.AdUnit;
import com.jd.entity.unit_condition.AdUnitDistrict;
import com.jd.entity.unit_condition.AdUnitIt;
import com.jd.entity.unit_condition.AdUnitKeyword;
import com.jd.entity.unit_condition.CreativeUnit;
import com.jd.exception.AdException;
import com.jd.service.IAdUnitService;
import com.jd.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class IAdUnitServiceImpl implements IAdUnitService {

    @Autowired(required = false)
    private AdPlanDao adPlanDao;
    @Autowired(required = false)
    private AdUnitDao adUnitDao;
    @Autowired(required = false)
    private AdUnitKeywordDao adUnitKeywordDao;
    @Autowired(required = false)
    private AdUnitDistrictDao adUnitDistrictDao;
    @Autowired(required = false)
    private AdUnitItDao adUnitItDao;
    @Autowired(required = false)
    private CreativeUnitDao creativeUnitDao;
    @Autowired(required = false)
    private AdCreativeDao adCreativeDao;

    @Override
    @Transactional
    public AdUnitResponse createUnit(AdUnitRequest request) throws AdException {
        if(!request.createValidate()){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        Long planId = request.getPlanId();
        List<AdPlan> allPlan = adPlanDao.findAllPlan(new AdPlan(planId));
        if(allPlan.isEmpty()){
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }
        List<AdUnit> allUnit = adUnitDao.findAllUnit(new AdUnit(request.getPlanId(), request.getUnitName(), request.getPositionType(), request.getBudget()));
        if(!allUnit.isEmpty()){
            throw new AdException(Constants.ErrorMsg.SAME_NAME_UNIT_ERROR);
        }
        AdUnit adUnit = new AdUnit(request.getPlanId(), request.getUnitName(),
                request.getPositionType(), request.getBudget());
        adUnitDao.save(adUnit);
        return new AdUnitResponse(adUnit.getId(),adUnit.getUnitName());
    }

    @Override
    @Transactional
    public AdUnitKeywordResponse createUnitKeyword(AdUnitKeywordRequest request) throws AdException {
        List<Long> ids = request.getUnitKeywords().stream().map(AdUnitKeywordRequest.UnitKeyword::getUnitId)
                .collect(Collectors.toList());
        if(!isRelatedUnitExist(ids)){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        List<AdUnitKeyword> adUnitKeywords = new ArrayList<>();
        if (!CollectionUtils.isEmpty(request.getUnitKeywords())) {
            request.getUnitKeywords().forEach(i->adUnitKeywords.add(new AdUnitKeyword(i.getUnitId(),i.getKeyword())));
            adUnitKeywordDao.saveAll(adUnitKeywords);
        }
        List<Long> all_id = new ArrayList<>();

        for(AdUnitKeyword adUnitKeyword : adUnitKeywords){
            all_id.add(adUnitKeyword.getId());
        }
        return new AdUnitKeywordResponse(all_id);
    }

    @Override
    @Transactional
    public AdUnitItResponse createUnitIt(AdUnitItRequest request) throws AdException {
        List<Long> ids = request.getUnitIts().stream().map(AdUnitItRequest.UnitIt::getUnitId).collect(Collectors.toList());
        if(!isRelatedUnitExist(ids)){
           throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        List<AdUnitIt> adUnitIts = new ArrayList<>();
        if(!CollectionUtils.isEmpty(request.getUnitIts())){
           request.getUnitIts().forEach(i->adUnitIts.add(new AdUnitIt(
                   i.getUnitId(),
                   i.getItTag()
           )));
           adUnitItDao.saveAll(adUnitIts);
        }
        List<Long> all_id = new ArrayList<>();
        adUnitIts.forEach(i->all_id.add(i.getId()));
        return new AdUnitItResponse(all_id);
    }

    @Override
    @Transactional
    public AdUnitDistrictResponse createUnitDistrict(AdUnitDistrictRequest request) throws AdException {
        List<Long> ids = request.getUnitDistricts().stream().map(AdUnitDistrictRequest.UnitDistrict::getUnitId).collect(Collectors.toList());
        if(!isRelatedUnitExist(ids)){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        List<AdUnitDistrict> adUnitDistricts = new ArrayList<>();
        request.getUnitDistricts().forEach(i->adUnitDistricts.add(
                new AdUnitDistrict(i.getUnitId(),i.getProvince(),i.getCity())
        ));
        adUnitDistrictDao.saveAll(adUnitDistricts);
        List<Long> all_id = new ArrayList<>();
        adUnitDistricts.forEach(i->all_id.add(i.getId()));
        return new AdUnitDistrictResponse(all_id);
    }

    @Override
    @Transactional
    public CreativeUnitResponse createCreativeUnit(CreativeUnitRequest request) throws AdException {
        List<Long> unitIds = request.getUnitItems().stream().map(CreativeUnitRequest.CreativeUnitItem::getUnitId).collect(Collectors.toList());
        List<Long> createIds = request.getUnitItems().stream().map(CreativeUnitRequest.CreativeUnitItem::getCreativeId).collect(Collectors.toList());
        if(! (isRelatedUnitExist(unitIds) && isRelatedCreativeExist(createIds))){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        List<CreativeUnit> creativeUnits = new ArrayList<>();
        request.getUnitItems().forEach(i->creativeUnits.add(
                new CreativeUnit(i.getUnitId(),i.getCreativeId())
        ));
        creativeUnitDao.saveAll(creativeUnits);
        List<Long> all_id = new ArrayList<>();
        creativeUnits.forEach(i->all_id.add(i.getId()));
        return new CreativeUnitResponse(all_id);
    }

    private boolean isRelatedUnitExist(List<Long> unitIds) {

        if (CollectionUtils.isEmpty(unitIds)) {
            return false;
        }

        return adUnitDao.findAllByIds(unitIds).size() ==
                new HashSet<>(unitIds).size();
    }

    private boolean isRelatedCreativeExist(List<Long> creativeIds) {

        if (CollectionUtils.isEmpty(creativeIds)) {
            return false;
        }

        return adCreativeDao.findAllByIds(creativeIds).size() ==
                new HashSet<>(creativeIds).size();
    }
}
