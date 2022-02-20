package com.jd.dao;


import com.jd.entity.AdUnit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdUnitDao {
    List<AdUnit> findAllUnit(AdUnit unit);
    void updateUnit(AdUnit unit);
    void save(AdUnit unit);
    List<AdUnit> findAllByIds(List<Long> ids);
}
