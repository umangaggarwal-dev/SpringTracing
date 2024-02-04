package org.umang.projects.kafkatracer.tracing.resttemplate;

import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.umang.projects.kafkatracer.tracing.interceptor.TracingInterceptor;

import java.util.Collections;
import java.util.List;

@Component
public class CustomRestTemplate extends RestTemplate {
    public CustomRestTemplate() {
        super();
        addRequestInterceptor();
    }

    public CustomRestTemplate(ClientHttpRequestFactory requestFactory) {
        super(requestFactory);
        addRequestInterceptor();
    }

    public CustomRestTemplate(List<HttpMessageConverter<?>> messageConverters) {
        super(messageConverters);
        addRequestInterceptor();
    }

    private void addRequestInterceptor() {
        List<ClientHttpRequestInterceptor> interceptors = Collections.singletonList(new TracingInterceptor());
        setInterceptors(interceptors);
    }
}
