package com.project.instagramclone.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
    //swagger 접속 링크: http://localhost:포트번호/swagger-ui/

    //1. controller class에 @Api 추가
    //@Api(tags = {"로그인"}) 추가하면 UserController의 태그가 "로그인"으로 달림

    //2. 각 api 에 mapping있는 부분에 @ApiOperation 추가
    //@ApiOperation(value = "기본 로그인", notes = "유저의 기본 로그인을 실행합니다.")

    @Bean
    public Docket restApi() {

        //JWT header 처리 용도
        List<Parameter> global = new ArrayList<>();

        global.add(new ParameterBuilder()
                .name("Authorization")
                .description("\"Bearer \" + Access Token")
                .parameterType("header")
                .required(false)
                .modelRef(new ModelRef("string"))
                .build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .globalOperationParameters(global)
                .ignoredParameterTypes(AuthenticationPrincipal.class)
                .select()
                //Swagger를 적용할 클래스의 package명
                .apis(RequestHandlerSelectors.basePackage("com.project.instagramclone"))
                //해당 package 하위에 있는 모든 url 적용
                .paths(PathSelectors.any())
                .build();
    }

    //해당 API에 대한 정보
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Instagram Clone Project REST API")
                .version("0.0.1")
                .description("인스타그램 클론코딩 프로젝트 REST API 입니다.")
                .build();
    }
}
