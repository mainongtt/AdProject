package com.jd.dao;

import com.jd.entity.AdPlan;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdPlanDao {
    List<AdPlan> findAllPlan(AdPlan plan);
    void updatePlan(AdPlan plan);
    void save(AdPlan plan);
}
