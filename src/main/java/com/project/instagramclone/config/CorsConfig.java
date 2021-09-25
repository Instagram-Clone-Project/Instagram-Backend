package com.project.instagramclone.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        //내 서버가 응답을 할 때 json을 자바스크립트에서 처리할 수 있게 할지를 설정
        config.setAllowCredentials(true);
        //모든 ip에 응답을 허용함
        config.addAllowedOrigin("*");
        //모든 Header에 응답을 허용함
        config.addAllowedHeader("*");
        //모든 Method(post, get, put, delete, patch)에 요청을 허용함
        config.addAllowedMethod("*");

        //모든 api/** 주소는 이 config 설정을 따라감
        source.registerCorsConfiguration("/api/**", config);

        return new CorsFilter(source);
    }
}
