package com.jd.dao.unit_condition;

import com.jd.entity.AdUnit;
import com.jd.entity.unit_condition.AdUnitKeyword;
import com.jd.entity.unit_condition.CreativeUnit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CreativeUnitDao {
    List<CreativeUnit> findAllCreativeUnit(CreativeUnit creativeUnit);
    void updateCreativeUnit(CreativeUnit creativeUnit);
    List<AdUnit> findAllByIds(List<Long> ids);
    void saveAll(List<CreativeUnit> creativeUnits);

}
