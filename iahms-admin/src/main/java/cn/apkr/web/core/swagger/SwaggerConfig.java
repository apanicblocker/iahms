package cn.apkr.web.core.swagger;

import cn.apkr.common.config.IahmsConfig;
import cn.apkr.common.constant.HttpStatus;
import cn.apkr.common.core.domain.AjaxResult;
import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.IntegerSchema;
import io.swagger.v3.oas.models.media.ObjectSchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.servers.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.customizers.OpenApiBuilderCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
@ConditionalOnProperty(name = "swagger.enabled", havingValue = "true")
public class SwaggerConfig {

    private static final Logger log = LoggerFactory.getLogger(SwaggerConfig.class);
    @Autowired
    private IahmsConfig iahmsConfig;

    @Value("${swagger.pathMapping}")
    private String pathMapping;

    /**
     * 创建API
     */
    @Bean
    public OpenAPI openAPI() {
        // 联系人信息(contact)，构建API的联系人信息，用于描述API开发者的联系信息，包括名称、URL、邮箱等
        // name：文档的发布者名称 url：文档发布者的网站地址，一般为企业网站 email：文档发布者的电子邮箱
        Contact contact = new Contact()
                .name(iahmsConfig.getName())                    // 作者名称
                .email("waoprkkr@qq.com")                       // 作者邮箱
                .url(null)                                      // 介绍作者的URL地址
                .extensions(new HashMap<String, Object>());     // 使用Map配置信息(如key为"name","email","url")

        // 授权许可信息(license)，用于描述API的授权许可信息，包括名称、URL等；假设当前的授权信息为Apache 2.0的开源标准
        License license = new License()
                .name("Apache 2.0")                             // 授权名称
                .url("http://springdoc.org")                    // 授权信息
                .identifier("Apache-2.0")                       // 授权许可标识
                .extensions(new HashMap<String, Object>());     // 使用Map配置信息(如key为"name","url","identifier")


        // 定义AjaxResult的Schema
        Schema<?> codeSchema = new IntegerSchema()
                .title("code")
                .description("响应码")
                .format("int32")
                .nullable(false)
                .example(HttpStatus.SUCCESS); // 示例状态码
        Schema<?> msgSchema = new StringSchema()
                .title("msg")
                .description("响应信息")
                .readOnly(true)
                .nullable(false)
                .example("操作成功"); // 示例消息
        Schema<?> dataSchema = new ObjectSchema()
                .title("data")
                .description("响应数据")
                .readOnly(true); // 数据可以是任意类型
        ObjectSchema ajaxResultSchema = new ObjectSchema();
        ajaxResultSchema.addProperty(AjaxResult.CODE_TAG, codeSchema);
        ajaxResultSchema.addProperty(AjaxResult.MSG_TAG, msgSchema);
        ajaxResultSchema.addProperty(AjaxResult.DATA_TAG, dataSchema);
        Components components = new Components();
        components.addSchemas("AjaxResult", ajaxResultSchema);

        // 返回信息
        return new OpenAPI()
                .components(components)
                .info(new Info()
                        .title("IA酒店管理系统_接口文档")          // Api接口文档标题(必填)
                        .description("用于管理酒店入住信息")       // Api接口文档描述
                        .version(iahmsConfig.getVersion())      // Api接口版本
                        .termsOfService("Api接口的服务条款地址")   // Api接口的服务条款地址
                        .license(license)                       // 授权许可信息
                        .contact(contact))                      // 设置联系人信息
                .addServersItem(new Server().url(pathMapping))  // 配置服务器信息
                .externalDocs(new ExternalDocumentation()
                        .description("外部文档")                 // 外部文档描述
                        .url("/doc.html"))                      // 外部文档链接
                .paths(new Paths());
    }

    @Bean
    public GroupedOpenApi sysApi() {
        return GroupedOpenApi.builder()
                .group("sys系统模块")
                .packagesToScan("cn.apkr.web.controller.system")
                .build();
    }

    @Bean
    public GroupedOpenApi hotelApi() {
        return GroupedOpenApi.builder()
                .group("hotel住房模块")
                .packagesToScan("cn.apkr.web.controller.hotel")
                .build();
    }

    @Bean
    public GroupedOpenApi toolApi() {
        return GroupedOpenApi.builder()
                .group("tool测试模块")
                .packagesToScan("cn.apkr.web.controller.tool")
                .build();
    }

//    @Bean
//    public GroupedOpenApi commonApi() {
//        return GroupedOpenApi.builder()
//                .group("通用模块")
//                .packagesToScan("cn.apkr.web.controller.common")
//                .build();
//    }
//
//    @Bean
//    public GroupedOpenApi payApi() {
//        return GroupedOpenApi.builder()
//                .group("支付模块")
//                .pathsToMatch("/pay/**")
//                .build();
//    }
}

