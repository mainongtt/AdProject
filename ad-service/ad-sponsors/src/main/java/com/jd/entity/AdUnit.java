package com.jd.entity;

import com.jd.constant.CommonStatus;
import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdUnit {
    private Long id;
    private Long planId;
    private String unitName;
    private Integer unitStatus;
    //广告位类型
    private Integer positionType;
    private Long budget;
    private Date createTime;
    private Date updateTime;

    public AdUnit(Long planId, String unitName, Integer positionType, Long budget) {
        this.planId = planId;
        this.unitName = unitName;
        this.positionType = positionType;
        this.budget = budget;
        this.unitStatus = CommonStatus.VALID.getStatus();
        this.createTime = new Date();
        this.updateTime = this.createTime;
    }
}
