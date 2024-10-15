package cn.apkr.web.core.swagger;

import cn.apkr.common.config.IahmsConfig;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@OpenAPIDefinition
//@EnableOpenApi
@Configuration
public class SwaggerConfig {

    @Autowired
    private IahmsConfig iahmsConfig;

    @Value("${swagger.enabled}")
    private boolean enabled;

    @Value("${swagger.pathMapping}")
    private String pathMapping;

    /**
     * 创建API
     */
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .groupName("iahms")
                .enable(enabled)
                .select()
//                .apis(RequestHandlerSelectors.withClassAnnotation(ApiOperation.class))
                .build()
                .pathMapping(pathMapping);
    }

    /**
     * 添加摘要信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("IA酒店管理系统_接口文档")
                .description("用于管理酒店入住信息")
                .contact(new Contact(iahmsConfig.getName(), null, null))
                .version(iahmsConfig.getVersion())
                .build();
    }
}
