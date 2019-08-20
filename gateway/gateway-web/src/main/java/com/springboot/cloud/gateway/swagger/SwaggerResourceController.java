package com.springboot.cloud.gateway.swagger;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger.web.*;

import java.util.List;

/**
 * Create by kim
 * on 2019-08-16 15:15
 * swagger聚合接口以及oauth认证配置
 * version 1.0
 */
@RestController
@RequestMapping("/swagger-resources")
public class SwaggerResourceController {

    @Autowired
    private SwaggerProvider swaggerProvider;

    @Autowired
    public SwaggerResourceController(SwaggerProvider swaggerProvider) {
        this.swaggerProvider = swaggerProvider;
    }

    @RequestMapping(value = "/configuration/security")
    public ResponseEntity<SecurityConfiguration> securityConfiguration() {
        return new ResponseEntity<>(createSecurity(), HttpStatus.OK);
    }

    @RequestMapping(value = "/configuration/ui")
    public ResponseEntity<UiConfiguration> uiConfiguration() {
        return new ResponseEntity<>(createUiConfig(), HttpStatus.OK);
    }

    @RequestMapping
    public ResponseEntity<List<SwaggerResource>> swaggerResources() {
        return new ResponseEntity<>(swaggerProvider.get(), HttpStatus.OK);
    }


    /**
     * 配置认证相关信息
     * @return
     */
    private SecurityConfiguration createSecurity() {
        return SecurityConfigurationBuilder.builder()
                .clientId("test_client")
                .clientSecret("test_secret")
                .realm("test-app-realm")
                .appName("test-app")
                .scopeSeparator(",")
                .additionalQueryStringParams(null)
                .useBasicAuthenticationWithAccessCodeGrant(false)
                .build();
    }


    private UiConfiguration createUiConfig() {
        return UiConfigurationBuilder.builder()
                .deepLinking(true)
                .displayOperationId(false)
                .defaultModelsExpandDepth(1)
                .defaultModelExpandDepth(1)
                .defaultModelRendering(ModelRendering.EXAMPLE)
                .displayRequestDuration(false)
                .docExpansion(DocExpansion.NONE)
                .filter(false)
                .maxDisplayedTags(null)
                .operationsSorter(OperationsSorter.ALPHA)
                .showExtensions(false)
                .tagsSorter(TagsSorter.ALPHA)
                .validatorUrl(null)
                .build();
    }
}
