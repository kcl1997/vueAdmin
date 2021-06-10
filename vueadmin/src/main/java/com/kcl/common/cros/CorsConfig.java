package com.kcl.common.cros;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



/**
 * 项目名： vueadmin
 * 包名:    com.kcl.common.cros
 * 文件名   CorsConfig
 * 创建者
 * 创建时间: 2021/6/4 8:00 P
 * 描述  解决跨域问题 -->  放弃，解决不了  --> spring sercuirty 在搞鬼
 */

//springboot 2.0以上的方式
//@Configuration
public class CorsConfig implements WebMvcConfigurer {
//    @Bean
//    public CorsWebFilter corsFilter() {
//        CorsConfiguration config = new CorsConfiguration();
//        config.addAllowedMethod("*");
//        config.addAllowedOrigin("*");
//        config.addAllowedHeader("*");
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
//        source.registerCorsConfiguration("/**", config);
//
//        return new CorsWebFilter(source);
//    }
//

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOriginPatterns("*")
//                .allowedMethods("GET","HEAD","POST","PUT","DELETE","OPTIONS")
//                .allowCredentials(false)
//                .allowedHeaders("*")
//                .maxAge(3600)
//                ;
//    }



}
