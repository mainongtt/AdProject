package com.jd.dao;


import com.jd.entity.AdCreative;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdCreativeDao {
    List<AdCreative> findAllCreative(AdCreative creative);
    void updateCreative(AdCreative creative);
    void save(AdCreative creative);
}
