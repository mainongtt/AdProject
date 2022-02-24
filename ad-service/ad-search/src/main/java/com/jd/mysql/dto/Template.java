package com.jd.mysql.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * 模版文件对应的实体
 */
public class Template {

    private String database;
    private List<JsonTable> tableList;
}
