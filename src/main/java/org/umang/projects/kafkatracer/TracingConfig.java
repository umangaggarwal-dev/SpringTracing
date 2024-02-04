package org.umang.projects.kafkatracer;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.umang.projects.kafkatracer.tracing.filter.TracingFilter;
import org.umang.projects.kafkatracer.tracing.resttemplate.CustomRestTemplate;

@Configuration
public class TracingConfig {

    @Bean
    public FilterRegistrationBean<TracingFilter> tracingFilter() {
        FilterRegistrationBean<TracingFilter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new TracingFilter());
        filterFilterRegistrationBean.addUrlPatterns("/*");
        filterFilterRegistrationBean.setOrder(1);
        System.out.println("Registered the tracing filter");
        return filterFilterRegistrationBean;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new CustomRestTemplate();
    }
}
