package com.github.umangaggarwal.springtracer;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import com.github.umangaggarwal.springtracer.tracing.filter.TracingFilter;
import com.github.umangaggarwal.springtracer.tracing.resttemplate.CustomRestTemplate;

@Configuration
public class TracingConfig {

    @Bean
    public FilterRegistrationBean<TracingFilter> tracingFilter() {
        FilterRegistrationBean<TracingFilter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new TracingFilter());
        filterFilterRegistrationBean.addUrlPatterns("/*");
        filterFilterRegistrationBean.setOrder(1);
        return filterFilterRegistrationBean;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new CustomRestTemplate();
    }
}
