package com.chaos.monkey.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Component로 빈으로 등록 하지 않는 이유눈 
 * 카오스몽키에서 와처를 restController, component를 동시에 true로 설정시 body missing 문제가 있어
 * 객체를 직접 생성하여 Bean로 등록 하였음
 */
@Configuration
public class HttpWrapperWrapperConfig {
    
    @Bean
    public HttpRequestWrapperFilter httpRequestWrapperFilter() {
        return new HttpRequestWrapperFilter();
    }
}
