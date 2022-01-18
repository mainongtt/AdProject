package com.jd.api.Test;

import io.swagger.annotations.*;


/**
 * @author zhang kun
 * @Classname TestControllerApi
 * @Description TODO
 * @Date 2022/1/10 21:04
 */
@Api(value="cms配置管理接口",description = "cms配置管理接口，提供数据模型的管理、查询接口")
public interface TestControllerApi {
    @ApiOperation("根据id查询CMS配置信息")
    String test();
}

