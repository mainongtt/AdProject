package com.jd.dao.unit_condition;


import com.jd.entity.unit_condition.AdUnitDistrict;
import com.jd.entity.unit_condition.AdUnitIt;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdUnitDistrictDao {

    List<AdUnitDistrict> findAllUnitDistrict(AdUnitDistrict unitDistrict);
    void updateUnitDistrict(AdUnitDistrict unitDistrict);
    void saveAll(List<AdUnitDistrict> adUnitDistricts);
}
