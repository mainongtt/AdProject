package com.jd.mysql.dto;


import com.jd.mysql.constant.OpType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * 表信息模板，从JsonTable 中提取
 */
public class TableTemplate {

    private String tableName;
    private String level;

    /**
     * 操作类型 -> 多个列
     */
    private Map<OpType, List<String>> opTypeFieldSetMap = new HashMap<>();

    /**
     * 字段索引 -> 字段名
     * */
    private Map<Integer, String> posMap = new HashMap<>();
}
