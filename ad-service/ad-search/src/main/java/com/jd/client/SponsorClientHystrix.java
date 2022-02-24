package com.jd.client;


import com.jd.client.vo.AdPlan;
import com.jd.client.vo.AdPlanGetRequest;
import com.jd.vo.CommonResponse;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class SponsorClientHystrix implements SponsorClient {

    @Override
    public CommonResponse<List<AdPlan>> getAdPlans(
            AdPlanGetRequest request) {
        return new CommonResponse<>(-1,
                "eureka-client-ad-sponsor error");
    }
}
