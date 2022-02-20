package com.jd.entity;

import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdCreative {
    private Long id;
    private String name;
    //物料类型(图片, 视频)
    private Integer type;
    //物料子类型
    private Integer materialType;

    private Integer height;

    private Integer width;
    private Long size;
    private Integer duration;
    private Integer auditStatus;
    private Long userId;
    private String url;
    private Date createTime;
    private Date updateTime;

    public AdCreative(String name, Integer type, Integer materialType, Integer width, Long size, Integer duration) {
        this.name = name;
        this.type = type;
        this.materialType = materialType;
        this.width = width;
        this.size = size;
        this.duration = duration;
        this.createTime = new Date();
        this.updateTime = this.createTime;
    }
}
