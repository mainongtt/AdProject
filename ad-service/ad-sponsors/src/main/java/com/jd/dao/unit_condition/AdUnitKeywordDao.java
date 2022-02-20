package com.jd.dao.unit_condition;


import com.jd.entity.unit_condition.AdUnitKeyword;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdUnitKeywordDao {
    List<AdUnitKeyword> findAllUnitKeyword(AdUnitKeyword unitKeyword);
    void updateUnitKeyword(AdUnitKeyword unitKeyword);
    void saveAll(List<AdUnitKeyword> adUnitKeywordList);
}
