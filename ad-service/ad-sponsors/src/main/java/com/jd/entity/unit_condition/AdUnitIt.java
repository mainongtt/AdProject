package com.jd.entity.unit_condition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitIt {

    private Long id;
    private Long unitId;
    private String itTag;

    public AdUnitIt(Long unitId, String itTag) {
        this.unitId = unitId;
        this.itTag = itTag;
    }
}
