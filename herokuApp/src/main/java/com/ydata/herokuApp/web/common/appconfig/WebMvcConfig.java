package com.ydata.herokuApp.web.common.appconfig;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <pre>
 * SPRIG WEB CONFIG
 * 
 * <<개정이력>>
 * 수정일                 수정자               수정내용
 * ----------     ----------    ---------------------
 * 2020.04.15.    조일근      최초 생성
 * </pre>
 *
 * @author ikcho <joynet9478@gmail.com>
 * @since 2020.04.15.
 * @version 1.0
 */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(WebMvcConfig.class);

    @Value("${resources.intercentor.exlist}") // application.properties에 설정된 값을 가지고 오기
    private List<String> notLoadList;

    @Autowired
    @Qualifier(value = "httpInterceptor")
    private HandlerInterceptor interceptor;

//    @Autowired
//    @Qualifier(value = "AuthInterceptor")
//    private HandlerInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        logger.debug(notLoadList.toString());
        registry.addInterceptor(interceptor).addPathPatterns("/**").excludePathPatterns(notLoadList);
//        registry.addInterceptor(authInterceptor).addPathPatterns("/**").excludePathPatterns(notLoadList);
    }

}