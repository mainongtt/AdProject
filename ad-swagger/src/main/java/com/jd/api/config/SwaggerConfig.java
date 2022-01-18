package com.jd.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;

import springfox.documentation.service.ApiInfo;

import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author zhang kun
 * @Classname SwaggerConfig
 * @Description TODO
 * @Date 2022/1/10 20:53
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(
                DocumentationType.SWAGGER_2)
                // 配置项目基本信息
                .apiInfo(apiInfo())
                .select()
                // 扫描的路径包,用于指定路径接口扫描设置
                .apis(RequestHandlerSelectors.basePackage("com.jd"))
                // 对所有路径进行监控
                .paths(PathSelectors.any())
                .build();
    }

    //生成接口信息，包括标题、联系人等
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 文档标题
                .title("后端服务接口文档")
                // 文档描述
                .description("客户端相关操作接口")
                // 文档版本
                .version("0.0.1")
                // 设置许可声明信息
                .license("Apache LICENSE 2.0")
                .build();
    }

}
