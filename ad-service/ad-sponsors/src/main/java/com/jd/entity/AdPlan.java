package com.jd.entity;

import com.jd.constant.CommonStatus;
import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdPlan {
    private Long id;
    private Long userId;
    private String planName;
    private Integer planStatus;
    private Date startDate;
    private Date endDate;
    private Date createTime;
    private Date updateTime;


    public AdPlan(Long id) {
        this.id = id;
    }

    public AdPlan(Long userId, String planName) {
        this.userId = userId;
        this.planName = planName;
    }

    public AdPlan(Long userId, String planName, Date startDate, Date endDate) {
        this.userId = userId;
        this.planName = planName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.planStatus = CommonStatus.VALID.getStatus();
        this.createTime = new Date();
        this.updateTime = this.createTime;
    }
}
