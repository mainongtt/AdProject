package com.jd.dao.unit_condition;

import com.jd.entity.unit_condition.AdUnitIt;
import com.jd.entity.unit_condition.AdUnitKeyword;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdUnitItDao {
    List<AdUnitIt> findAllUnitIt(AdUnitIt unitIt);
    void updateUnitIt(AdUnitIt unitIt);
    void saveAll(List<AdUnitIt> adUnitItList);
}
