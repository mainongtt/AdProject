package com.jd.service;

import com.jd.dao.AdPlanDao;
import com.jd.entity.AdPlan;
import com.jd.exception.AdException;
import com.jd.vo.AdPlanGetRequest;
import com.jd.vo.AdPlanRequest;
import com.jd.vo.AdPlanResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AdPlanServiceTest {
    @Autowired
    private IAdPlanService adPlanService;

    @Test
    public void update() throws AdException {
        AdPlanRequest adPlanRequest = new AdPlanRequest(10L,15L,"推广计划名称1","2021-11-01","2022-01-11");
        AdPlanResponse adPlanResponse = adPlanService.updateAdPlan(adPlanRequest);
        System.out.println(adPlanResponse.getId());
        System.out.println(adPlanResponse.getPlanName());
    }

    @Autowired
    private AdPlanDao adPlanDao;
    @Test
    public void select(){

        List<AdPlan> allPlan = adPlanDao.findAllPlan(new AdPlan(10L));
        System.out.println(allPlan.get(0).getPlanName());
    }
}
