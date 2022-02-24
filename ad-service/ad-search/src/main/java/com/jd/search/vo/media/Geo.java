package com.jd.search.vo.media;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 位置信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Geo {

    private Float latitude; //经度纬度
    private Float longitude;

    private String city;
    private String province;
}
