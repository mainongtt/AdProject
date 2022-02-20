package com.jd.dao;

import com.jd.entity.AdUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface AdUserDao {
    List<AdUser> findAllUser(AdUser user);
    void updateUser(AdUser user);
    int save(AdUser user);
}
